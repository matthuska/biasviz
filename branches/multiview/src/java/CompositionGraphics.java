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
import javax.swing.*;

class CompositionGraphics extends JPanel {

    Rule aarule;
    CompositionPlot plot;
    JScrollPane scroll;
    SequenceLabels names;
    public static final Color SCROLL_BACKGROUND = Color.WHITE;

    CompositionGraphics(PlotModel model) {
        plot = new CompositionPlot(model);
        scroll = new JScrollPane(plot);
        aarule = new Rule(model, Rule.HORIZONTAL);
        names = new SequenceLabels(model.getCoreModel());
        names.setPreferredHeight((int)(model.getCoreModel().numSeqs() *
                    model.getCoreModel().getZoomHeight()));

        this.layoutView();
    }

    void layoutView() {
        this.setLayout(new BorderLayout());
        scroll.setColumnHeaderView(aarule);
        scroll.setRowHeaderView(names);
        scroll.setCorner(JScrollPane.UPPER_LEFT_CORNER, new JPanel());
        this.setScrollBackground();
        this.add(scroll);
    }

    void setScrollBackground() {
        this.plot.setBackground(SCROLL_BACKGROUND);
        scroll.getCorner(JScrollPane.UPPER_LEFT_CORNER).setBackground(SCROLL_BACKGROUND);
        scroll.getRowHeader().setBackground(SCROLL_BACKGROUND);
        scroll.getColumnHeader().setBackground(SCROLL_BACKGROUND);
    }
}
