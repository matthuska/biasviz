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

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.Math;
import java.awt.geom.AffineTransform;

class CompositionPlot extends JPanel implements IView {

    CompositionModel model;
    BufferedImage image;

    public static final int GAPCOLOUR = (new Color(255, 0, 0)).getRGB();

    boolean dynamicIntensity;

    CompositionPlot(CompositionModel model) {
        assert model != null;
        this.model = model;

        this.image = new BufferedImage(999, 999, BufferedImage.TYPE_INT_RGB);
        parseAlignment(model.getAlignment());
        this.layoutView();

        this.model.addView(this);
    }

    public void layoutView() {
        // Needed for scrollbars to show up correctly
        Dimension mySize = new Dimension((int)(image.getWidth() * model.getZoomWidth()),
                                         (int)(image.getHeight() * model.getZoomHeight()));
        setSize(mySize);
        setPreferredSize(mySize);
    }

    public void updateView() {
        // Recalculate image matrix
        parseAlignment(model.getAlignment());

        Dimension mySize = new Dimension((int)(image.getWidth() * model.getZoomWidth()),
                                         (int)(image.getHeight() * model.getZoomHeight()));
        setSize(mySize);
        setPreferredSize(mySize);
        this.repaint(this.getVisibleRect());

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // We need simple scaling so that the image doesn't appear blurred
        g2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION, 
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        g2.drawRenderedImage(image, AffineTransform.getScaleInstance(
                                        model.getZoomWidth(),
                                        model.getZoomHeight()));


    }

    public Dimension getSize() {
      return getPreferredSize();
    }

    // Takes in an alignment and returns a visualization of it.
    private void parseAlignment(Alignment align) {

        int height = align.size();
        int width = align.maxLength();

        if (height == 0 || width == 0) {
            return;
        }

        int windowSize = model.getWindowSize();
        int halfWindowSize = windowSize / 2;
        String acids = model.getAminoAcids();

        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int[][] matchArray = new int[height][width];
        int matchMax = 0;

        for (int seq = 0; seq < height; seq++) {

            Sequence sobj = align.getSequence(seq);
            String seqGaps = sobj.getSequence();
            String seqNoGaps = sobj.getSequenceNoGaps();

            int nongaps = 0; // Number of non-gap positions processed
            int prevNongap = 0; // Location of last non-gap

            for (int aa = 0; aa < seqGaps.length(); aa++) {
                if (seqGaps.charAt(aa) == '-') {
                    matchArray[seq][aa] = -1;
                    continue;
                }

                int startpos = Math.max(0, nongaps - halfWindowSize);
                int endpos = Math.min(seqNoGaps.length() - 1, nongaps + halfWindowSize);
                assert startpos <= endpos;

                int matches = 0;

                // Old & unoptimized
                //for (int pos = startpos; pos <= endpos; pos++) {
                //    for (int acidpos = 0; acidpos < acids.length(); acidpos++) {
                //        if (seqNoGaps.charAt(pos) == acids.charAt(acidpos)) {
                //            matches += 1;
                //        }
                //    }
                //}

                // New and slightly optimized
                if (nongaps == 0) {
                    // Compute the whole window (should only happen once per sequence)
                    for (int pos = startpos; pos <= endpos; pos++) {
                        for (int acidpos = 0; acidpos < acids.length(); acidpos++) {
                            if (seqNoGaps.charAt(pos) == acids.charAt(acidpos)) {
                                matches += 1;
                            }
                        }
                    }
                } else {
                    // Use value from last window computation to calculate this position (much faster for large windows)
                    matches = matchArray[seq][prevNongap];
                    if (startpos > 0) {
                        for (int acidpos = 0; acidpos < acids.length(); acidpos++) {
                            if (seqNoGaps.charAt(startpos - 1) == acids.charAt(acidpos)) {
                                matches -= 1;
                            }
                        }
                    }

                    if (endpos < seqNoGaps.length() - 1) {
                        for (int acidpos = 0; acidpos < acids.length(); acidpos++) {
                            if (seqNoGaps.charAt(endpos) == acids.charAt(acidpos)) {
                                matches += 1;
                            }
                        }
                    }
                }

                matchArray[seq][aa] = matches;
                if (matches > matchMax) {
                    matchMax = matches;
                }

                nongaps += 1;
                prevNongap = aa;
            }

            // FIXME: fill in rest of row. Is this needed? Gap coloured or transparent?
            Color empty = new Color(0, 0, 255);
            int emptyRGB = empty.getRGB();
            for (int k = sobj.size(); k < width; k++) {
                image.setRGB(k, seq, emptyRGB);
            }

        }


        for (int seq = 0; seq < height; seq++) {
            Sequence sobj = align.getSequence(seq);
            String seqGaps = sobj.getSequence();
            for (int aa = 0; aa < seqGaps.length(); aa++) {

                if (matchArray[seq][aa] < 0) {
                    image.setRGB(aa, seq, GAPCOLOUR);
                    continue;
                }

                int divisor;
                int shade;

                if (model.getDisplayType() == model.DISPLAY_DYNAMIC) {
                    divisor = matchMax;
                    shade = (int)(( (double)matchArray[seq][aa] / divisor) * 255.0d);
                } else if (model.getDisplayType() == model.DISPLAY_FIXED) {
                    divisor = windowSize;
                    shade = (int)(( (double)matchArray[seq][aa] / divisor) * 255.0d);
                } else {
                    if (((float)matchArray[seq][aa] / windowSize) >= 
                            (model.getDisplayThreshold() / 100.0f)) {
                        shade = 255;
                    } else {
                        shade = 0;
                    }
                }

                Color c = new Color(shade, shade, shade);
                shade = c.getRGB();
                image.setRGB(aa, seq, shade);

            }
        }
    }

}

