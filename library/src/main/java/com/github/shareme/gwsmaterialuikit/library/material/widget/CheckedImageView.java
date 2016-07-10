/*
 * Copyright 2015 Rey Pham.
 * Modifications Copyright(C) 2016 Fred Grott
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.github.shareme.gwsmaterialuikit.library.material.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;

/**
 * Created by Rey on 9/16/2015.
 */
public class CheckedImageView extends ImageView implements Checkable {

    private boolean mChecked = false;

    private static final int[] STATE_CHECKED = new int[]{
        android.R.attr.state_checked
    };

    public CheckedImageView(Context context) {
        super(context);
    }

    public CheckedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckedImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean b) {
        if(mChecked != b){
            mChecked = b;
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return mChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);

        int[] additionalStates = mChecked ? STATE_CHECKED : null;
        if (additionalStates != null)
            mergeDrawableStates(drawableState, additionalStates);

        return drawableState;
    }
}
