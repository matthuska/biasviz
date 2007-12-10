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

public class CompositionModel implements PlotModel, IView {

    //final static float ZOOM_WIDTH_STEP = 1.2f; 
    //final static float ZOOM_HEIGHT_STEP = 1.2f; 

    final static int DISPLAY_DYNAMIC    = 1;
    final static int DISPLAY_FIXED      = 2;
    final static int DISPLAY_THRESHOLD  = 3;

    List<IView> views;
    CoreModel coreModel;

    //String plotName; // type of plot

    String aminoAcids;
    int windowSize;

    ///* Zoom level */
    //float zoomWidth;
    //float zoomHeight;

    ///* Dynamically adjust intensity */
    int displayType;
    int displayThreshold;

    public CompositionModel(CoreModel m) {
        assert m != null;
        coreModel = m;

        this.views = new CopyOnWriteArrayList<IView>();

    //    // Set some defaults
        this.aminoAcids = "A";
        this.windowSize = 100;
        this.displayType = DISPLAY_DYNAMIC;
        this.displayThreshold = 15;
    //    this.plotName = "Hydrophobicity Plot 2";

    //    this.zoomWidth = 1.0f;
    //    this.zoomHeight = 10.0f;

        coreModel.addView(this);
    }

    /* For Controller and View interaction */

    public String getAminoAcids() {
        return aminoAcids;
    }

    public void setAminoAcids(String aa) {
        String aaOnly = Utilities.stripNonAAUnique(aa.toUpperCase());
        
        if (!this.aminoAcids.equals(aaOnly)) {
            this.aminoAcids = aaOnly;
            this.updateAllViews();
        }
    }

    public void setDisplayType(int d) {
        if (d != displayType) {
            displayType = d;
            this.updateAllViews();
        }
    }

    public int getDisplayType() {
        return displayType;
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

    public int getWindowSize() {
        return this.windowSize;
    }

    public void setWindowSize(int size) {
        if (this.windowSize != size) {
            this.windowSize = size;
            this.updateAllViews();
        }
    }


    //public float getZoomWidth() { return 1.0; }

    //public float getZoomHeight() { return zoomHeight; }
    //public float getZoomWidth() { return zoomWidth; }

    //public void setZoomHeight(float zoom) {
    //    this.zoomHeight = zoom;
    //}

    //// Increase zoom level for width
    //public void incZoomWidth() {
    //    this.zoomWidth *= ZOOM_WIDTH_STEP;
    //    this.updateAllViews();
    //}

    //// Decrease zoom level for width
    //public void decZoomWidth() {
    //    this.zoomWidth /= ZOOM_WIDTH_STEP;
    //    this.updateAllViews();
    //}

    //// Reset zoom level to 1:1
    //public void zoomWidth1to1() {
    //    this.zoomWidth = 1.0f;
    //    this.updateAllViews();
    //}

    public CoreModel getCoreModel() {
        return coreModel;
    }

    public void setCoreModel(CoreModel m) {
        coreModel = m;
    }

    public void updateView() {
    }

    /*
     * View management
     */
    public void addView(IView view) {
        views.add(view);
    }

    public void removeView(IView view) {
        views.remove(view);
    }

    private void updateAllViews() {
        for (IView view : views) {
            view.updateView();
        }
    }
}

