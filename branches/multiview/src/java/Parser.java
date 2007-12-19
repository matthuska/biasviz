/*
 * Copyright (c) 2007 Matthew R. Huska
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 */

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Parser {

    static public Alignment parseFasta(String input) {
        Alignment alignment = new Alignment();
        BufferedReader in = new BufferedReader(new StringReader(input));
        String line = null;
        String name = null;
        StringBuffer seq = new StringBuffer();

        try {
            while ((line = in.readLine()) != null) {
                if (line.length() > 0 && line.charAt(0) == '>') {
                    // Start of header
                    if (name != null) {
                        // save current sequence
                        Sequence s = new Sequence(name, seq.toString());
                        alignment.addSequence(s);
                        seq = new StringBuffer();
                    }
                    String header = line.split("\\s+")[0];
                    name = header.substring(1); // strip off '>'
                } else if (line.length() > 0 && line.charAt(0) == ';') {
                    // Start of comment
                    continue;
                } else {
                    String pureLine = line.replaceAll("\\s", "");
                    seq.append(pureLine.toUpperCase());
                    // Sequence data
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error reading Fasta format input.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (name != null) {
            // save current sequence
            Sequence s = new Sequence(name, seq.toString());
            alignment.addSequence(s);
        }

        return alignment;
    }

    static public String parseJPred(String input) {
        String secondary = "";

        // Remove empty lines
        String inputNoEmpty = input.trim().replaceAll("\n+", "\n");

        String[] lines = inputNoEmpty.split("\n");
        int numLines = lines.length;
        if (numLines == 1) {
            secondary = lines[0];
        } else if (numLines == 2) {
            secondary = lines[1];
        } else {
            // What to do here? Return null or ""?
        }

        return secondary;
    }

    /*
     * Read in user provided data. Ensure that it is the correct length.
     *
     * pre: alignment passed in is associated with this data
     * post: return populated UserData object or throw exception if there is a
     * problem parsing
     *
     */
    static public UserData parseUserData(String input, Alignment align) throws Exception {
        UserData ud = new UserData();
        BufferedReader in = new BufferedReader(new StringReader(input));
        String line = null;
        String name = null;
        List<Float> data = new ArrayList<Float>();

        try {
            while ((line = in.readLine()) != null) {
                if (line.length() > 0 && line.charAt(0) == '>') {
                    // Start of header
                    if (name != null) {
                        // save current sequence
                        ud.add(name, data);
                        data = new ArrayList<Float>();
                    }
                    String header = line.split("\\s+")[0];
                    name = header.substring(1); // strip off '>'
                } else if (line.length() > 0 && line.charAt(0) == ';') {
                    // Start of comment
                    continue;
                } else {
                    if (line.trim().length() > 0) {
                        // Convert tabs to commas to handle tab separated columns
                        line = line.replace('\t', ',');
                        String[] splitLine = line.split(",");
                        if (splitLine.length >= 3) {
                            data.add(Float.parseFloat(splitLine[2].trim()));
                        } else {
                            System.err.println("Line too short: " + line);
                        }
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error reading user entered data.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (name != null) {
            // save current sequence
            ud.add(name, data);
        }

        // Check length of data matches alignment
        List<String> names = ud.getNames();
        for (String myname : names) {
            Sequence s = align.getSequence(myname);
            String seqNoGaps = s.getSequenceNoGaps();
            int lengthSequence = seqNoGaps.length();
            int lengthData = ud.getData(myname).size();

            if (lengthSequence != lengthData) {
                System.err.println(">" + myname);
                System.err.println("Sequence length: " + lengthSequence);
                System.err.println("Data length: " + lengthData);
                throw new Exception();
            }
        }

        return ud;
    }

}

