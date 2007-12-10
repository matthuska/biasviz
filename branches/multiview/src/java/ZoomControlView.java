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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.URL;

public class ZoomControlView extends JPanel implements IView {

    CompositionModel model;

    Icon zoom1to1Icon;
    Icon zoomInIcon;
    Icon zoomOutIcon;

    JButton zoomIn;
    JButton zoom1to1;
    JButton zoomOut;

    public ZoomControlView(CompositionModel model) {
        assert model != null;
        this.model = model;

        this.layoutView();

        this.registerControllers();

        this.model.addView(this);

    }

    public void layoutView() {
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        URL zoom100Url = getClass().getResource("icons/gtk-zoom-100.png");
        URL zoomInUrl = getClass().getResource("icons/gtk-zoom-in.png");
        URL zoomOutUrl = getClass().getResource("icons/gtk-zoom-out.png");

        Toolkit tk = Toolkit.getDefaultToolkit();

        try {

            Image zoom100Image = tk.createImage(zoom100Url);
            Image zoomInImage = tk.createImage(zoomInUrl);
            Image zoomOutImage = tk.createImage(zoomOutUrl);

            zoom1to1Icon = new ImageIcon(zoom100Image);
            zoomInIcon = new ImageIcon(zoomInImage);
            zoomOutIcon = new ImageIcon(zoomOutImage);

        } catch (Exception e) {
            System.err.println(e.getCause());
        }

        this.zoomOut = new JButton(zoomOutIcon);
        this.zoom1to1 = new JButton(zoom1to1Icon);
        this.zoomIn = new JButton(zoomInIcon);

        this.zoomOut.setToolTipText("Zoom Out");
        this.zoom1to1.setToolTipText("Zoom to 1:1");
        this.zoomIn.setToolTipText("Zoom In");

        // Glue allows the buttons to float to the right
        this.add(Box.createHorizontalGlue());
        this.add(zoomOut);
        this.add(zoom1to1);
        this.add(zoomIn);
        
    }

    public void updateView() {
    }

    /* Controllers using anonymous classes */
    public void registerControllers() {
        this.zoomIn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                model.incZoomWidth();
            }
        });

        this.zoom1to1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                model.zoomWidth1to1();
            }
        });

        this.zoomOut.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                model.decZoomWidth();
            }
        });
    }
}
