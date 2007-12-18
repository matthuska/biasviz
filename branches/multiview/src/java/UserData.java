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

import java.util.*;

class UserData {

    List<String> names;
    List<List<Float>> data;

    public UserData() {
        names = new ArrayList<String>();
        data = new ArrayList<List<Float>>();
    }

    public void add(String name, List<Float> d) {
        names.add(name);
        data.add(d);
    }

    public float get(int seq, int pos) {
        return (data.get(seq)).get(pos);
    }

    public List<Float> getData(String name) {
        for (int i = 0; i < names.size(); i++) {
            if (name.equals(names.get(i))) {
                return data.get(i);
            }
        }
        return null;
    }

    public int size() {
        return names.size();
    }

    public List<String> getNames() {
        return names;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < names.size(); i++) {
            buf.append(names.get(i) + "\n" + data.get(i) + "\n");
        }
        return buf.toString();
    }

}
