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
 * Entry point for the program.
 *
 * @author mhuska
 */

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class CompositionMain {

    // not needed for applet version
    public static void main(String[] args) {
        String input = null;
        String secondary = null;
        String userData = null;

        JFrame f = new JFrame("BiasViz");
        f.setSize(800, 600);
        f.setLayout(new BorderLayout());

        CompositionApplet comp = new CompositionApplet();

        if (args.length > 0) { input = readInput(args[0]); }
        if (args.length > 1) { secondary = readInput(args[1]); }
        if (args.length > 2) { userData = readInput(args[2]); }

        comp.init(input, secondary, userData);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(comp);

        f.setVisible(true);
    }

    public static String readInput(String filename) {
          StringBuffer contents = new StringBuffer();
          BufferedReader input = null;
          try {

              input = new BufferedReader( new FileReader(filename) );
              String line = null; //not declared within while loop

              while (( line = input.readLine()) != null){
                  contents.append(line);
                  contents.append(System.getProperty("line.separator"));
              }
          }
          catch (FileNotFoundException ex) {
              ex.printStackTrace();
          }
          catch (IOException ex){
              ex.printStackTrace();
          }
          finally {
              try {
                  if (input!= null) {
                      //flush and close both "input" and its underlying FileReader
                      input.close();
                  }
              }
              catch (IOException ex) {
                  ex.printStackTrace();
              }
          }
          return contents.toString();
    }

}

