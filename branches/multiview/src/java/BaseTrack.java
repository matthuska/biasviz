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

import java.awt.*;
import javax.swing.*;

abstract class BaseTrack {

    protected BasePlotModel plotModel;
    protected BaseControls controls;
    protected BasePlot plot;

    private void setModel(BasePlotModel newModel) {
        assert newModel != null;
        plotModel = newModel;
    }

    public BasePlotModel getModel() {
        return plotModel;
    }

    private void setControls(BaseControls newControls) {
        assert newControls != null;
        controls = newControls;
    }

    public boolean hasControls() {
        return (controls != null);
    }

    public BaseControls getControls() {
        return controls;
    }

    private void setPlot(BasePlot newPlot) {
        assert newPlot != null;
        plot = newPlot;
    }

    public BasePlot getPlot() {
        return plot;
    }

    public SequenceLabels getLabels() {
        return plot.getLabels();
    }

    protected abstract String getName();

    public abstract String toString();

}

