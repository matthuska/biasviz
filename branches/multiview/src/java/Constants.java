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

import java.util.Map;
import java.util.Hashtable;

class Constants {

    public static final String AMINO_ACIDS = "ACDEFGHIKLMNPQRSTVWY";

    private static final Map<Character, Float> HYDROPHOBICITY =
            new Hashtable<Character, Float>(20);

    // Eisenberg Consensus
    static {
        HYDROPHOBICITY.put(new Character('A'), new Float(0.62));
        HYDROPHOBICITY.put(new Character('R'), new Float(-2.53));
        HYDROPHOBICITY.put(new Character('N'), new Float(-0.78));
        HYDROPHOBICITY.put(new Character('D'), new Float(-0.78));
        HYDROPHOBICITY.put(new Character('C'), new Float(0.29));
        HYDROPHOBICITY.put(new Character('Q'), new Float(-0.85));
        HYDROPHOBICITY.put(new Character('E'), new Float(-0.74));
        HYDROPHOBICITY.put(new Character('G'), new Float(0.48));
        HYDROPHOBICITY.put(new Character('H'), new Float(-0.4));
        HYDROPHOBICITY.put(new Character('I'), new Float(1.38));
        HYDROPHOBICITY.put(new Character('L'), new Float(1.06));
        HYDROPHOBICITY.put(new Character('K'), new Float(-1.5));
        HYDROPHOBICITY.put(new Character('M'), new Float(0.64));
        HYDROPHOBICITY.put(new Character('F'), new Float(1.19));
        HYDROPHOBICITY.put(new Character('P'), new Float(0.12));
        HYDROPHOBICITY.put(new Character('S'), new Float(-0.18));
        HYDROPHOBICITY.put(new Character('T'), new Float(-0.05));
        HYDROPHOBICITY.put(new Character('W'), new Float(0.81));
        HYDROPHOBICITY.put(new Character('Y'), new Float(0.26));
        HYDROPHOBICITY.put(new Character('V'), new Float(1.08));
    }
}
