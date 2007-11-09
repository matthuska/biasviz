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
 * Our lovely graphical view of the amino acid composition of the input alignment.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GraphicView extends JPanel implements IView {

    CompositionModel model;

    CompositionPlot plot;
    JScrollPane scroll;
    Rule aarule;
    SequenceLabels names;
    ZoomControlView zoomControls;

    // Used for mouse panning of the view
    int mouseLastX;

    public GraphicView(CompositionModel model) {
        assert model != null;
        this.model = model;

        this.layoutView();

        this.registerControllers();

        this.model.addView(this);

    }

    public void layoutView() {
        this.setLayout(new BorderLayout());

        this.zoomControls = new ZoomControlView(model);

        this.plot = new CompositionPlot(model);
        this.plot.setBackground(Color.WHITE);

        this.scroll = new JScrollPane(plot);
        // Use wheel for zoom instead of default action.
        this.scroll.setWheelScrollingEnabled(false);

        this.aarule = new Rule(model, Rule.HORIZONTAL);
        this.names = new SequenceLabels(model);

        names.setPreferredHeight((int)(model.numSeqs() * model.getZoomHeight()));

        scroll.setColumnHeaderView(aarule);
        scroll.setRowHeaderView(names);

        this.add(zoomControls, BorderLayout.SOUTH);
        this.add(scroll, BorderLayout.CENTER);
    }

    public void updateView() {
    }

    public void registerControllers() {

        // Zoom using mouse wheel
        // FIXME: Have zoom center on area where mouse pointer is.
        this.plot.addMouseWheelListener(new MouseWheelListener()
        {
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.getWheelRotation() < 0) {
                    model.incZoomWidth();
                } else {
                    model.decZoomWidth();
                }
            }

        });

        // Click and drag to pan image
        this.plot.addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int diff = e.getX() - mouseLastX;

                    JViewport vp = scroll.getViewport();
                    int newX = (int)vp.getViewPosition().getX() - diff;

                    // Prevent from scrolling beyond ends of the plot/image
                    if (newX < 0) {
                        newX = 0;
                    } else {
                        int rightX = (int)plot.getPreferredSize().getWidth() - 
                                scroll.getHorizontalScrollBar().getVisibleAmount();

                        if (newX > rightX) {
                            newX = rightX;
                        }
                    }

                    scroll.getViewport().setViewPosition(new Point(newX, 0));

                    // Need to subtract "diff" here because viewport has moved
                    // and changed the location of the last mouse location
                    // relative to the graphical view
                    mouseLastX = e.getX() - diff;
                }
            }
        });

        this.plot.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    mouseLastX = e.getX();
                }
            }
        });
    }
}

