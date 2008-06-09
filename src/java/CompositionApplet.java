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
 * Parse input and create core objects.
 *
 * @author mhuska
 */

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.*;
import java.awt.*;

public class CompositionApplet extends JApplet {

    public void init(String input) {
        // Load and parse input
        setLookAndFeel();
        Alignment align = new Alignment();
        CompositionModel model = new CompositionModel(align);

        model.setAlignment(input);

        CompositionUI ui = new CompositionUI(model);
        this.add(ui);
    }

    public void init() {
        int lines = Integer.parseInt(getParameter("numlines"));
        StringBuffer buf = new StringBuffer();
        
        for (int i = 0; i < lines; i++) {
            buf.append(getParameter("line" + i) + "\n");
        }
        init(buf.toString());
    }

    /**
     * This is the default constructor
     *
     */
    public void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

