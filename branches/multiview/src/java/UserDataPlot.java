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
import java.util.List;

class UserDataPlot extends BasePlot {

    boolean dynamicIntensity;

    UserDataPlot(BasePlotModel m) {
        super(m);
    }

    protected void recalculate() {
        UserDataModel cmodel = (UserDataModel)model;
        CoreModel core = cmodel.getCoreModel();
        Alignment align = core.getAlignment();
        UserData ud = core.getUserData();

        int height = align.numSequences();
        int width = align.maxLength();

        if (height == 0 || width == 0) {
            return;
        }

        int windowSize = cmodel.getWindowSize();
        int halfWindowSize = windowSize / 2;

        float[][] scoreArray = new float[height][width];
        float maxScore = 0.0f;

        for (int seq = 0; seq < height; seq++) {

            Sequence sobj = align.getSequence(seq);
            String seqGaps = sobj.getSequence();
            String seqNoGaps = sobj.getSequenceNoGaps();
            String name = sobj.getName();
            java.util.List<Float> data = ud.getData(name);

            int nonGaps = 0;

            for (int aa = 0; aa < seqGaps.length(); aa++) {
                if (seqGaps.charAt(aa) == '-') {
                    scoreArray[seq][aa] = -1.0f;
                    continue;
                }

                int startpos = Math.max(0, nonGaps - halfWindowSize);
                int endpos = Math.min(seqNoGaps.length() - 1, nonGaps - halfWindowSize + windowSize);
                int realWindowSize = endpos - startpos;
                assert startpos < endpos;

                float score = 0.0f;

                // Old & unoptimized
                for (int pos = startpos; pos < endpos; pos++) {
                    score += (float)data.get(pos);
                }

                score /= realWindowSize;
                scoreArray[seq][aa] = score;

                if (score > maxScore) {
                    maxScore = score;
                }

                nonGaps++;
            }
        }

        // Convert score matrix into image. Should make this separate method.
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int seq = 0; seq < height; seq++) {
            Sequence sobj = align.getSequence(seq);
            String seqGaps = sobj.getSequence();
            for (int aa = 0; aa < seqGaps.length(); aa++) {

                if (scoreArray[seq][aa] < 0) {
                    image.setRGB(aa, seq, GAP_RGB);
                    continue;
                }

                float divisor;
                float shade;

                if (cmodel.getDisplayType() == cmodel.DISPLAY_DYNAMIC) {
                    shade = scoreArray[seq][aa] / maxScore;
                } else if (cmodel.getDisplayType() == cmodel.DISPLAY_FIXED) {
                    shade = scoreArray[seq][aa];
                } else {
                    if (scoreArray[seq][aa] >= (cmodel.getDisplayThreshold() / 100.0f)) {
                        shade = 1.0f;
                    } else {
                        shade = 0.0f;
                    }
                }

                Color c = new Color(shade, shade, shade);
                int color_int = c.getRGB();
                image.setRGB(aa, seq, color_int);

            }
        }
    }
}

