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
import java.util.List;
import java.util.ArrayList;

public class SequenceLabels extends JComponent implements IView {

    List<String> names = new ArrayList<String>();
    CoreModel model;

    SequenceLabels(String name) {
        names.add(name);
        updateView();
    }

    SequenceLabels(List<String> newNames) {
        names = newNames;
        updateView();
    }

    SequenceLabels(CoreModel model) {
//        assert model != null;
//        this.model = model;
//
        this.names = model.getAlignment().getNames();

//        this.model.addView(this);
        updateView();
    }

    public void setMaximumHeight(int ph) {
        setMaximumSize(new Dimension(100, ph));
    }

    public void setMaximumWidth(int pw) {
        setMaximumSize(new Dimension(pw, 30));
    }

    public void setPreferredHeight(int ph) {
        setPreferredSize(new Dimension(100, ph));
    }

    public void setPreferredWidth(int pw) {
        setPreferredSize(new Dimension(pw, 30));
    }

    public void setMinimumHeight(int ph) {
        setMinimumSize(new Dimension(100, ph));
    }

    public void setMinimumWidth(int pw) {
        setMinimumSize(new Dimension(pw, 30));
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Rectangle drawHere = g2.getClipBounds();

        // Fill clipping area with dirty brown/orange.
        g.setColor(this.getBackground());
        g.fillRect(drawHere.x, drawHere.y, drawHere.width, drawHere.height);

        g2.setFont(new Font("SansSerif", Font.PLAIN, 9));
        g2.setColor(Color.BLACK);

        for (int i = 0; i < names.size(); i++) {
            g2.drawString(names.get(i), 10, 8 + (i * 10));
        }

    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (String name: names) {
            buf.append(name + "\n");
        }
        return buf.toString();
    }

    public void updateView() {
        //this.names = model.getAlignment().getNames();

        setPreferredWidth(50);
        setMinimumWidth(50);
        setMaximumWidth(50);

        setPreferredHeight(names.size() * 10);
        setMinimumHeight(names.size() * 10);
        setMaximumHeight(names.size() * 10);

        this.repaint();
    }

}

