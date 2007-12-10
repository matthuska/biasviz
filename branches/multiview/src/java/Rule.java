import java.awt.*;
import javax.swing.*;
import java.util.Random;

/* 
 * Modified version of file from Sun tutorial at:
 * http://java.sun.com/docs/books/tutorial/uiswing/examples/components/ScrollDemoProject/src/components/Rule.java
 *
 */

public class Rule extends JComponent implements IView {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int SIZE = 35;

    public int orientation;
    private int increment;

    private PlotModel model;

    public Rule(PlotModel m, int o) {
        assert m != null;
        this.model = m;

        orientation = o;
        setIncrement();

        this.layoutView();

        this.model.addView(this);
    }

    public void updateView() {
        setIncrement();
        //setPreferredWidth(100);
        setPreferredWidth((int)(model.getCoreModel().maxLength() * model.getCoreModel().getZoomWidth()));
        this.repaint();
    }

    private void layoutView() {
        setPreferredWidth((int)(model.getCoreModel().maxLength() * model.getCoreModel().getZoomWidth()));
    }


    private void setIncrement() {
        increment = 50;
    }

    public int getIncrement() {
        return increment;
    }

    public void setPreferredHeight(int ph) {
        setPreferredSize(new Dimension(SIZE, ph));
    }

    public void setPreferredWidth(int pw) {
        setPreferredSize(new Dimension(pw, SIZE));
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Rectangle drawHere = g2.getClipBounds();

        // Fill clipping area with dirty brown/orange.
        g.setColor(this.getBackground());
        g.fillRect(drawHere.x, drawHere.y, drawHere.width, drawHere.height);

        // Do the ruler labels in a small font that's black.
        g2.setFont(new Font("SansSerif", Font.PLAIN, 10));
        g2.setColor(Color.black);

        // Some vars we need.
        int end = 0;
        int start = 0;
        int tickLength = 0;
        String text = null;

        // Use clipping bounds to calculate first and last tick locations.
        if (orientation == HORIZONTAL) {
            start = (drawHere.x / increment) * increment;
            end = (((drawHere.x + drawHere.width) / increment) + 1)
                  * increment;
        } else {
            start = (drawHere.y / increment) * increment;
            end = (((drawHere.y + drawHere.height) / increment) + 1)
                  * increment;
        }

        // Make a special case of 0 to display the number
        // within the rule and draw a units label.
        if (start == 0) {
            text = Integer.toString(0) + " aa";
            tickLength = 10;
            if (orientation == HORIZONTAL) {
                g2.drawLine(0, SIZE-1, 0, SIZE-tickLength-1);
                g2.drawString(text, 2, 21);
            } else {
                g2.drawLine(SIZE-1, 0, SIZE-tickLength-1, 0);
                g2.drawString(text, 9, 10);
            }
            text = null;
            start = increment;
        }

        // ticks and labels
        for (int i = start; i < end; i += increment) {
            //if (i % units == 0)  {
            tickLength = 10;
            //text = Integer.toString(10);
            text = Integer.toString((int)(i/model.getCoreModel().getZoomWidth()));
            //} else {
            //tickLength = 7;
            //text = null;
            //}

            if (tickLength != 0) {
                if (orientation == HORIZONTAL) {
                    g.drawLine(i, SIZE-1, i, SIZE-tickLength-1);
                    if (text != null)
                        g2.drawString(text, i-3, 21);
                } else {
                    g.drawLine(SIZE-1, i, SIZE-tickLength-1, i);
                    if (text != null)
                        g2.drawString(text, 9, i+3);
                }
            }
        }
    }
}

