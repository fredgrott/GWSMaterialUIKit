/*
 The MIT License (MIT)

Copyright (c) [2016] [Sundeepk]
Modifications Copyright(C) 2016 Fred Grott(GrottWorkShop)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 */
package com.github.shareme.gwsmaterialuikit.library.ccv;


import android.view.animation.Animation;
import android.view.animation.Transformation;

class CollapsingAnimation extends Animation {
    private final int targetHeight;
    private final CompactCalendarView view;
    private final boolean down;
    private float currentGrow;
    private CompactCalendarController compactCalendarController;

    public CollapsingAnimation(CompactCalendarView view, CompactCalendarController compactCalendarController, int targetHeight, boolean down) {
        this.view = view;
        this.compactCalendarController = compactCalendarController;
        this.targetHeight = targetHeight;
        this.down = down;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        currentGrow+=2.4;
        float grow = 0;
        int newHeight;
        if (down) {
            newHeight = (int) (targetHeight * interpolatedTime);
            grow = (float) (interpolatedTime * (targetHeight * 2));
        } else {
            float progress = 1 - interpolatedTime;
            newHeight = (int) (targetHeight * progress);
            grow = (float) (progress * (targetHeight * 2));
        }
        compactCalendarController.setGrowProgress(grow);
        view.getLayoutParams().height = newHeight;
        view.requestLayout();

    }

    @Override
    public void initialize(int width, int height, int parentWidth,
                           int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}