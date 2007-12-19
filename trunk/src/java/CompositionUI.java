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
 * Split up the interface into different areas.
 *
 * @author mhuska
 */

import java.awt.*;
import javax.swing.*;

public class CompositionUI extends JPanel implements IView {

    CompositionModel model;
    GraphicView gv;
    ControlView cv;

    CompositionUI(CompositionModel model) {
        super();
        assert model != null;
        this.model = model;

        this.gv = new GraphicView(model);
        this.cv = new ControlView(model);

        this.layoutView();
        this.model.addView(this);
    }

    /*
     * Layout the applet window.
     */
    private void layoutView() {
        this.setLayout(new BorderLayout());

        this.add(gv, BorderLayout.CENTER);

        cv.setBorder(BorderFactory.createTitledBorder("Settings"));
        this.add(cv, BorderLayout.SOUTH);
    }

    public void updateView() {
        // Do nothing for now
    }

}
