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
import java.util.List;
import java.io.*;

public class Alignment {

    List<Sequence> sequences;

    public Alignment() {
        sequences = new ArrayList<Sequence>();
    }

    public List<Sequence> getSequences() {
        return sequences;
    }

    public void addSequence(Sequence s) {
        sequences.add(s);
    }

    public Sequence getSequence(int index) {
        return sequences.get(index);
    }

    public Sequence getSequence(String name) {
        for (Sequence seq : sequences) {
            if (name.equals(seq.getName())) {
                return seq;
            }
        }
        return null;
    }

    public List<String> getNames() {
        List<String> names = new ArrayList<String>();
        for (Sequence seq : sequences) {
            names.add(seq.getName());
        }
        return names;
    }

    public String getSequenceName(int i) {
        return sequences.get(i).getName();
    }

    // Returns the number of sequences in the alignment
    public int numSequences() {
        return sequences.size();
    }

    // Returns the length of the longest sequence in the alignment.
    public int maxLength() {
        int max = 0;
        for (Sequence seq : sequences) {
            if (seq.size() > max) {
                max = seq.size();
            }
        }
        return max;
    }

    public String toString() {
        StringBuffer output = new StringBuffer();
        for (Sequence seq : sequences) {
            output.append(seq.getName());
            output.append(System.getProperty("line.separator"));
            output.append(seq.getSequence());
            output.append(System.getProperty("line.separator"));
        }
        return output.toString();
    }
}

