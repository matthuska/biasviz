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

import java.util.Set;
import java.util.HashSet;

class Utilities {

    public static String stripNonAAUnique(String input) {
        String notAA = "[^" + Constants.AMINO_ACIDS + "]";
        String replaced = input.replaceAll(notAA, "");

        return Utilities.unique(replaced);
    }

    public static String unique(String nonunique) {
        Set<Character> unique = new HashSet<Character>();

        for (int i = 0; i < nonunique.length(); i++) {
            if (!unique.contains(nonunique.charAt(i))) {
                unique.add(nonunique.charAt(i));
            }
        }

        StringBuffer buf = new StringBuffer();

        for (int j = 0; j < Constants.AMINO_ACIDS.length(); j++) {
            if (unique.contains(Constants.AMINO_ACIDS.charAt(j))) {
                buf.append(Constants.AMINO_ACIDS.charAt(j));
            }
        }

        return buf.toString();
    }
}

