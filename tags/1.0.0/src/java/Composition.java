/*
 * For trying out Graphics programming in Java.
 */

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.*;
import java.util.Random;

public class Composition extends Applet {
//public class HelloWorldApplet extends Panel {

    Random generator;

    // not needed for applet version
    public static void main(String[] args) {
        Frame f = new Frame("Test java app");
        f.setSize(800, 600);
        Composition hw = new Composition();
        hw.init();
        f.add(hw);
        //f.show(); // deprecated in Java 5
        f.setVisible(true);
    }

    public void init() {
        //generator = new Random(201442); // use my own seed for testing
        generator = new Random();
    }

    public void paint(Graphics g) {
        
        int bw = 2;
        int bh = 5;

        int red;
        int green;
        int blue;

        g.drawString("Hello woorld!", 50, 25);
        for (int i = 0; i < 400; i++) {
            for (int j = 0; j < 10; j++) {
                red = generator.nextInt(255);
                green = generator.nextInt(255);
                blue = generator.nextInt(255);

                g.setColor(new Color(red, green, blue));

                g.fillRect(5 + (i * bw), 5 + (j * bh), bw, bh);
            }
        }
    }
}

