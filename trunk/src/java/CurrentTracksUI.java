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
import java.io.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

class CurrentTracksUI extends JPanel implements IView {

    CoreModel model;

    JScrollPane scrollTracks;
    JList tracks;
    JButton removeTracks;

    public CurrentTracksUI(CoreModel m) {
        super();

        assert m != null;
        this.model = m;

        createWidgets();
        layoutView();
        registerControllers();

        this.model.addView(this);
    }

    private void createWidgets() {
        tracks = new JList(this.model.getTracks().toArray());
        tracks.setLayoutOrientation(JList.VERTICAL);
        scrollTracks = new JScrollPane(tracks);
        scrollTracks.setMinimumSize(new Dimension(0, 100));
        removeTracks = new JButton("Remove");
    }

    private void layoutView() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(scrollTracks);
        this.add(removeTracks);
    }

    public void updateView() {
        this.tracks.setListData(this.model.getTracks().toArray(new BaseTrack[0]));
    }

    private void registerControllers() {
        this.removeTracks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] selectedTracks = CurrentTracksUI.this.tracks.getSelectedValues();
                for (Object selectedTrack : selectedTracks) {
                    CurrentTracksUI.this.model.removeTrack(selectedTrack);
                }
            }
        });
    }

}
