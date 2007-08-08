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

public class Parser {

    public Alignment parseFasta(String input) {
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
            System.err.println("Exception parsing fasta text.");
        }
        if (name != null) {
            // save current sequence
            Sequence s = new Sequence(name, seq.toString());
            alignment.addSequence(s);
        }
        return alignment;
    }

    public Alignment parseClustalW(String input) {
        return new Alignment();
    }

}

