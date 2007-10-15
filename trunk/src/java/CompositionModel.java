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

public class CompositionModel {

    final static float ZOOM_WIDTH_STEP = 1.2f; 
    final static float ZOOM_HEIGHT_STEP = 1.2f; 

    final static int DISPLAY_DYNAMIC    = 1;
    final static int DISPLAY_FIXED      = 2;
    final static int DISPLAY_THRESHOLD  = 3;

    ArrayList<IView> views;

    String rawinput;
    Alignment alignment;
    String aminoAcids;
    int windowSize;
    int stepSize;

    /* Zoom level */
    float zoomWidth;
    float zoomHeight;

    /* Dynamically adjust intensity */
    int displayType;
    int displayThreshold;

    public CompositionModel() {
        this(new Alignment());
    }

    public CompositionModel(Alignment align) {
        this.alignment = align;

        this.views = new ArrayList<IView>();

        this.aminoAcids = "A";
        this.windowSize = 100;
        this.stepSize = 1;
        this.displayType = DISPLAY_DYNAMIC;
        this.displayThreshold = 15;

        this.zoomWidth = 1.0f;
        this.zoomHeight = 10.0f;
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

    // *************************************************
    // Zoom related
    public float getZoomHeight() { return zoomHeight; }

    public float getZoomWidth() { return zoomWidth; }

    public void setZoomHeight(float zoom) {
        this.zoomHeight = zoom;
    }

    // Increase zoom level for width
    public void incZoomWidth() {
        this.zoomWidth *= ZOOM_WIDTH_STEP;
        this.updateAllViews();
    }

    // Decrease zoom level for width
    public void decZoomWidth() {
        this.zoomWidth /= ZOOM_WIDTH_STEP;
        this.updateAllViews();
    }

    // Returns the composition data in comma separated value format
    public String getCSV() {
        Alignment align = alignment;
        System.err.println("Parsing");

        StringBuffer csvString = new StringBuffer();

        int height = align.size();
        int width = align.maxLength();

        if (height == 0 || width == 0) {
            return "";
        }

        int windowSize = this.getWindowSize();
        int halfWindowSize = windowSize / 2;
        String acids = this.getAminoAcids();

        csvString.append("\"Amino Acid Composition Values\"\n");
        csvString.append("\"\"\n");
        csvString.append("\"Settings:\"\n");
        csvString.append("\" Window size: " + windowSize + "\"\n");
        csvString.append("\" Amino acid(s): " + acids + "\"\n");

        // Iterate through sequences
        for (int seq = 0; seq < height; seq++) {

            csvString.append("\n\">" + align.getSequenceName(seq) + "\"\n");

            Sequence sobj = align.getSequence(seq);
            String seqGaps = sobj.getSequence();
            String seqNoGaps = sobj.getSequenceNoGaps();

            int nongaps = 0; // Number of non-gap positions processed

            // Iterate through individual amino acids in the current sequence
            for (int aa = 0; aa < seqGaps.length(); aa++) {
                if (seqGaps.charAt(aa) == '-') {
                    continue;
                }

                int startpos = Math.max(0, nongaps - halfWindowSize);
                int endpos = Math.min(seqNoGaps.length() - 1, nongaps + halfWindowSize);
                assert startpos <= endpos;

                int matches = 0;

                for (int pos = startpos; pos <= endpos; pos++) {
                    for (int acidpos = 0; acidpos < acids.length(); acidpos++) {
                        if (seqNoGaps.charAt(pos) == acids.charAt(acidpos)) {
                            matches += 1;
                        }
                    }
                }

                double comp = (double)matches / windowSize;
                csvString.append((nongaps + 1) + ", " + comp + ", \"" + seqNoGaps.charAt(nongaps) + "\"\n");
                nongaps += 1;
            }
        }

        return csvString.toString();
    }

    // *************************************************
    
    /* Misc useful methods */

    public String toString() {
        return alignment.toString();
    }

    public int maxLength() {
        return alignment.maxLength();
    }

    public int numSeqs() {
        return alignment.size();
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(String input) {
        Parser parser = new Parser();
        alignment = parser.parseFasta(input);
        rawinput = input;
        this.updateAllViews();
    }

    /*
     * View management
     */
    public void addView(IView view) {
        views.add(view);
    }

    public void removeView(IView view) {
        this.views.remove(view);
    }

    public void updateAllViews() {
        Iterator v_it = views.iterator();
        while (v_it.hasNext()) {
            IView view = (IView)v_it.next();
            view.updateView();
        }
    }
}

