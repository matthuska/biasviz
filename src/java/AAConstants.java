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

import java.util.HashMap;
import java.util.TreeMap;

class AAConstants {

    public final static String AMINO_ACIDS = "ACDEFGHIKLMNPQRSTVWY";

    //public final static TreeMap AMINO_ACID_GROUPS = new TreeMap();
    public final static TreeMap AMINO_ACID_GROUPS = new TreeMap();
    static {
        //AMINO_ACID_GROUPS.put("",    "");
        AMINO_ACID_GROUPS.put("Hydrophobic",    "ILVCAGMFYWHT");
        AMINO_ACID_GROUPS.put("Polar",          "YWHKREQDNSTP");
        AMINO_ACID_GROUPS.put("Small",          "VCAGDNSTP");
        AMINO_ACID_GROUPS.put("Tiny",           "AGS");
        AMINO_ACID_GROUPS.put("Aliphatic",      "ILV");
        AMINO_ACID_GROUPS.put("Aromatic",       "FYWH");
        AMINO_ACID_GROUPS.put("Positive",       "HKR");
        AMINO_ACID_GROUPS.put("Negative",       "ED");
        AMINO_ACID_GROUPS.put("Charged",        "HKRED");
        AMINO_ACID_GROUPS.put("Alcohols",       "ST");
    }

}

