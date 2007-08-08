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

import java.util.ArrayList;
import java.io.*;

public class Alignment {

    ArrayList sequences;

    public Alignment() {
        sequences = new ArrayList();
    }

    public ArrayList getSequences() {
        return sequences;
    }

    public void addSequence(Sequence s) {
        sequences.add(s);
    }

    public Sequence getSequence(int index) {
        return (Sequence)sequences.get(index);
    }

    public ArrayList<String> getSequenceNames() {
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < sequences.size(); i++) {
            Sequence seq = (Sequence)sequences.get(i);
            names.add(seq.getName());
        }
        return names;
    }

    public String getSequenceName(int i) {
        return ((Sequence)sequences.get(i)).getName();
    }

    // Returns the number of sequences in the alignment
    public int size() {
        return sequences.size();
    }

    // Returns the length of the longest sequence in the alignment.
    public int maxLength() {
        int max = 0;

        for (int i = 0; i < sequences.size(); i++) {
            Sequence seq = (Sequence)sequences.get(i);
            if (seq.size() > max) {
                max = seq.size();
            }
        }
        return max;
    }

    public String toString() {
        // For fun
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < sequences.size(); i++) {
            Sequence s = (Sequence)sequences.get(i);
            output.append(s.getName());
            output.append(System.getProperty("line.separator"));
            output.append(s.getSequence());
            output.append(System.getProperty("line.separator"));
        }
        return output.toString();
    }
}

