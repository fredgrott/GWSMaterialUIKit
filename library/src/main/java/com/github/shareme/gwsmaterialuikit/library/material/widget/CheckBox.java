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

import com.github.shareme.gwsmaterialuikit.library.material.drawable.CheckBoxDrawable;

@SuppressWarnings("unused")
public class CheckBox extends CompoundButton {

    public CheckBox(Context context) {
        super(context);
    }

    public CheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

	public CheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

    @Override
    protected void applyStyle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super.applyStyle(context, attrs, defStyleAttr, defStyleRes);

        CheckBoxDrawable drawable = new CheckBoxDrawable.Builder(context, attrs, defStyleAttr, defStyleRes).build();
        drawable.setInEditMode(isInEditMode());
        drawable.setAnimEnable(false);
        setButtonDrawable(drawable);
        drawable.setAnimEnable(true);
    }

    /**
     * Change the checked state of this button immediately without showing animation.
     * @param checked The checked state.
     */
    public void setCheckedImmediately(boolean checked){
        if(getButtonDrawable() instanceof CheckBoxDrawable){
            CheckBoxDrawable drawable = (CheckBoxDrawable)getButtonDrawable();
            drawable.setAnimEnable(false);
            setChecked(checked);
            drawable.setAnimEnable(true);
        }
        else
            setChecked(checked);
    }

}
