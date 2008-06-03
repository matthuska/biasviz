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
 *
 * @author mhuska
 */

import java.awt.*;
import javax.swing.*;
import java.util.Map;
import java.util.Hashtable;

public class CompositionTrack extends BaseTrack {

    CompositionTrack(CoreModel coreModel) {
        plotModel = new CompositionModel(coreModel);
        controls = new CompositionControls((CompositionModel)plotModel);
        plot = new CompositionPlot(plotModel);
    }

    public String getName() {
        return "Amino Acid Composition";
    }

    public String toString() {
        return "Multiple Sequence Alignment";
    }

    public Map<String, String> getSettings() {
        Map<String, String> settings = new Hashtable<String, String>();
        CompositionModel compModel = (CompositionModel)plotModel;
        settings.put("Window Size", Integer.toString(compModel.getWindowSize()));
        settings.put("Amino Acids", compModel.getAminoAcids());
        settings.put("Display Type", Integer.toString(compModel.getDisplayType()));
        settings.put("Display Threshold", Integer.toString(compModel.getDisplayThreshold()));
        return settings;
    }

    public String getData() {
        return new String();
    }
}


