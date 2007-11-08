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

public class GraphicView extends JPanel implements IView {

    CompositionModel model;

    CompositionPlot plot;
    JScrollPane scroll;
    Rule aarule;
    SequenceLabels names;
    ZoomControlView zoomControls;

    public GraphicView(CompositionModel model) {
        assert model != null;
        this.model = model;

        this.layoutView();

        this.model.addView(this);

    }

    public void layoutView() {
        this.setLayout(new BorderLayout());

        this.zoomControls = new ZoomControlView(model);

        this.plot = new CompositionPlot(model);
        this.plot.setBackground(Color.WHITE);

        this.scroll = new JScrollPane(plot);

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

}

