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
 *
 * @author mhuska
 */

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CoreModel {

    List<IView> views;
    List<BaseTrack> tracks;

    String rawInput;
    Alignment alignment;
    String secondary;
    UserData userData;

    /* Zoom level */
    float zoomWidth;
    float zoomHeight;
    final static float ZOOM_WIDTH_STEP = 1.2f; 
    final static float ZOOM_HEIGHT_STEP = 1.2f; 

    public CoreModel() {
        alignment = new Alignment();
        tracks = new ArrayList<BaseTrack>();
        rawInput = "";
        zoomWidth = 1.0f;
        zoomHeight = 10.0f;
        secondary = null;
        this.views = new CopyOnWriteArrayList<IView>();
    }

    public void addTrack(BaseTrack track) {
        tracks.add(track);
        updateAllViews();
    }

    public void removeTrack(BaseTrack track) {
        tracks.remove(track);
        updateAllViews();
    }

    public List<BaseTrack> getTracks() {
        return tracks;
    }

    public void setAlignment(Alignment aln, String raw) {
        alignment = aln;
        rawInput = raw;
        this.updateAllViews();
    }

    public Alignment getAlignment() {
        return (alignment == null) ? new Alignment() : alignment;
    }

    public boolean hasAlignment() {
        return (alignment == null) ? true : false;
    }

    public void setUserData(UserData input) {
        this.userData = input;
        this.updateAllViews();
    }

    public UserData getUserData() {
        return userData;
    }

    public void setSecondary(String input) {
        secondary = Parser.parseJPred(input);
        this.updateAllViews();
    }

    public String getSecondary() {
        return secondary;
    }

    // FIXME: compute this when secondary is first set and save for future use
    public String getSecondaryWithGaps() {
        String seq = alignment.getSequence(0).getSequence();
        StringBuilder swg = new StringBuilder();
        int j = 0;
        // FIXME: Rewrite to use indexOf or something like that
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == '-') {
                swg.append('_');
            } else {
                swg.append(secondary.charAt(j));
                j++;
            }
        }
        return swg.toString();
    }
    
    /* Misc useful methods */

    public String toString() {
        return alignment.toString();

    }

    public int maxLength() {
        return alignment.maxLength();
    }

    public int numSeqs() {
        return alignment.numSequences();
    }

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

    // Reset zoom level to 1:1
    public void zoomWidth1to1() {
        this.zoomWidth = 1.0f;
        this.updateAllViews();
    }

    /* View management */

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

