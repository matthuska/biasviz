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

public class MainApplet extends JApplet {

    CoreModel model;
    // Layout that contains the file loading interface and main UI
    JPanel cards; 
    JPanel loader;
    JPanel ui;
    JPanel save;

    public void init() {
        setLookAndFeel();

        model = new CoreModel();
 
        cards = new JPanel(new CardLayout());

        loader = new LoaderUI(model, cards);
        ui = new CoreUI(model, cards);
        save = new SaveUI(model, cards);

        cards.add(loader, "Loader");
        cards.add(ui, "Main");
        cards.add(save, "Save");

        this.add(cards);
    }

    public void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            UIManager.put("Slider.paintValue", Boolean.FALSE);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

