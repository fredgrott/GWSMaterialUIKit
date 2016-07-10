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
import android.support.v7.widget.ListViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.github.shareme.gwsmaterialuikit.library.material.app.ThemeManager;
import com.github.shareme.gwsmaterialuikit.library.material.util.ViewUtil;


public class ListView extends ListViewCompat implements ThemeManager.OnThemeChangedListener{

	private RecyclerListener mRecyclerListener;

    protected int mStyleId;
    protected int mCurrentStyle = ThemeManager.THEME_UNDEFINED;

	public ListView(Context context) {
		super(context);

		init(context, null, 0, 0);
    }

    public ListView(Context context, AttributeSet attrs) {
    	super(context, attrs);

    	init(context, attrs, 0, 0);
    }

    public ListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr, 0);
    }

    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){

    	super.setRecyclerListener(new RecyclerListener() {

			@Override
			public void onMovedToScrapHeap(View view) {
				RippleManager.cancelRipple(view);

				if(mRecyclerListener != null)
					mRecyclerListener.onMovedToScrapHeap(view);
			}

		});

        if(!isInEditMode())
            mStyleId = ThemeManager.getStyleId(context, attrs, defStyleAttr, defStyleRes);
    }

    public void applyStyle(int resId){
        ViewUtil.applyStyle(this, resId);
        applyStyle(getContext(), null, 0, resId);
    }

    protected void applyStyle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
    }

    @Override
    public void onThemeChanged(ThemeManager.OnThemeChangedEvent event) {
        int style = ThemeManager.getInstance().getCurrentStyle(mStyleId);
        if(mCurrentStyle != style){
            mCurrentStyle = style;
            applyStyle(mCurrentStyle);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(mStyleId != 0) {
            ThemeManager.getInstance().registerOnThemeChangedListener(this);
            onThemeChanged(null);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(mStyleId != 0)
            ThemeManager.getInstance().unregisterOnThemeChangedListener(this);
    }

    @Override
    public void setRecyclerListener(RecyclerListener listener) {
    	mRecyclerListener = listener;
    }

}
