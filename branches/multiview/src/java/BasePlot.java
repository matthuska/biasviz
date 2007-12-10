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

public abstract class BasePlot extends JPanel implements IView {

    protected PlotModel model;
    protected BufferedImage image;
    protected BufferedImage simage;
    protected String name;

    public static final int GAP_RGB   = Color.red.getRGB();
    public static final int HELIX_RGB = Color.green.getRGB();
    public static final int BETA_RGB  = (new Color(0, 100, 255)).getRGB();
    public static final int BLANK_RGB  = Color.black.getRGB();

    //boolean dynamicIntensity;

    BasePlot(PlotModel pm) {
        assert pm != null;
        this.model = pm;

        this.image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);

        //Alignment align = model.getCoreModel().getAlignment();
        //parseAlignment(model.getCoreModel().getAlignment());

        //if (model.hasSecondary()) {
        //    parseSecondary(model.getSecondaryWithGaps());
        //}

        this.layoutView();

        this.model.addView(this);
        this.updateView();
    }

    public void layoutView() {
        CoreModel c = model.getCoreModel();

        // Needed for scrollbars to show up correctly
        Dimension mySize = new Dimension((int)(image.getWidth() * c.getZoomWidth() + 1),
                                         (int)(image.getHeight() * c.getZoomHeight() + 1));
        setSize(mySize);
        setPreferredSize(mySize);
    }

    public void updateView() {
        // Recalculate image matrix
        parseAlignment(model.getCoreModel().getAlignment());
//        if (model.hasSecondary()) {
//            parseSecondary(model.getSecondaryWithGaps());
//        }

        CoreModel c = model.getCoreModel();
        Dimension mySize = new Dimension((int)(image.getWidth() * c.getZoomWidth() + 1),
                                         (int)(image.getHeight() * c.getZoomHeight() + 1));
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
        CoreModel c = model.getCoreModel();

        AffineTransform a1 = AffineTransform.getScaleInstance(
                                        c.getZoomWidth(),
                                        c.getZoomHeight());

//        g2.drawRenderedImage(simage, a1);
//        a1.translate(0, 1);
        g2.drawRenderedImage(image, a1);

        // Draw border around image
        g2.setColor(Color.black);
        g2.drawRect(0, 0, 
                (int)(c.getZoomWidth() * c.getAlignment().maxLength()),
                (int)(c.getZoomHeight() * c.getAlignment().numSequences())
                );

    }

    public Dimension getSize() {
      return getPreferredSize();
    }

    // Takes in an alignment and returns a visualization of it.
    protected abstract void parseAlignment(Alignment align);

    // Draw secondary structure.
    protected void parseSecondary(String secondary) {
    //    int width = model.maxLength();
    //    this.simage = new BufferedImage(width, 1, BufferedImage.TYPE_INT_RGB);
    //    if (width == 0) {
    //        return;
    //    }
    //    String sec = model.getSecondaryWithGaps();

    //    for (int i = 0; i < sec.length(); i++) {
    //        if (sec.charAt(i) == 'H') {
    //            simage.setRGB(i, 0, HELIX_RGB);
    //        } else if (sec.charAt(i) == 'E') {
    //            simage.setRGB(i, 0, BETA_RGB);
    //        } else if (sec.charAt(i) == '_') {
    //            simage.setRGB(i, 0, GAP_RGB);
    //        } else {
    //            simage.setRGB(i, 0, BLANK_RGB);
    //        }
    //    }
    }

    protected void finalize() {
        model.removeView(this);
    }
}

