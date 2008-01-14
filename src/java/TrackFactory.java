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

//import java.util.ArrayList;
//import java.util.List;

class TrackFactory {

//    final static String[] TrackTypes = {
//        CompositionTrack.ge"Amino Acid Composition",
//        "User Provided Values",
//        "JPred Secondary Structure",
//        "Test UI",
//    };

    static BaseTrack getTrack(String name, CoreModel model) {
        if (name.equals(CompositionTrack.getName())) {
            return new CompositionTrack(model);
//        } else if (name.equals("User Provided Values")) {
//            return new UserDataUI(model);
//        } else if (name.equals(SecondaryTrack.getName())) {
//            return new SecondaryTrack(model);
//        } else if (name.equals("Test UI")) {
//            return new TestUI(model);
        }
        System.err.println("User interface not found.");
        return null;
    }

//    static String[] getTrackNames() {
//        return TrackTypes;
//    }
}
