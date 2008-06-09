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

class SecondaryPlot extends BasePlot {

    boolean dynamicIntensity;

    SecondaryPlot(BasePlotModel m) {
        super(m);
    }

    protected void setLabels() {
        CoreModel c = model.getCoreModel();
        String name = c.getAlignment().getSequenceName(0);
        names = new SequenceLabels(name);
    }

    // Draw secondary structure.
    protected void recalculate() {
        CoreModel c = model.getCoreModel();
        String secondary = c.getSecondary();
        int width = c.maxLength();
        this.image = new BufferedImage(width, 1, BufferedImage.TYPE_INT_RGB);
        if (width == 0) {
            return;
        }
        String sec = c.getSecondaryWithGaps();

        for (int i = 0; i < sec.length(); i++) {
            if (sec.charAt(i) == 'H') {
                image.setRGB(i, 0, HELIX_RGB);
            } else if (sec.charAt(i) == 'E') {
                image.setRGB(i, 0, BETA_RGB);
            } else if (sec.charAt(i) == '_') {
                image.setRGB(i, 0, GAP_RGB);
            } else {
                image.setRGB(i, 0, BLANK_RGB);
            }
        }
    }

}

