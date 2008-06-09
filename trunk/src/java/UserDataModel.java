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

/**
 * Model to hold the amino acid composition data. Includes the parameters as
 * well as sequences and their names
 *
 * @author mhuska
 */

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserDataModel extends BasePlotModel implements IView {

    final static int DISPLAY_DYNAMIC    = 1;
    final static int DISPLAY_FIXED      = 2;
    final static int DISPLAY_THRESHOLD  = 3;

    int windowSize;

    ///* Dynamically adjust intensity */
    int displayType;
    int displayThreshold;

    public UserDataModel(CoreModel m) {
        assert m != null;
        coreModel = m;

        this.views = new CopyOnWriteArrayList<IView>();

        // Set some defaults
        this.displayType = DISPLAY_DYNAMIC;
        this.displayThreshold = 15;
        this.windowSize = 1;

        coreModel.addView(this);
    }

    /* For Controller and View interaction */

    public void setDisplayType(int d) {
        if (d != displayType) {
            displayType = d;
            this.updateAllViews();
        }
    }

    public int getDisplayType() {
        return displayType;
    }

    public int getWindowSize() {
        return this.windowSize;
    }

    public void setWindowSize(int size) {
        if (this.windowSize != size) {
            this.windowSize = size;
            this.updateAllViews();
        }
    }

    public void setDisplayThreshold(int t) {
        if (t != displayThreshold) {
            displayThreshold = t;
            this.updateAllViews();
        }
    }

    public int getDisplayThreshold() {
        return displayThreshold;
    }

}

