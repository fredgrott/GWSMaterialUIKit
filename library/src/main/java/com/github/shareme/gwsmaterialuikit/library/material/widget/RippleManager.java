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
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.github.shareme.gwsmaterialuikit.library.R;
import com.github.shareme.gwsmaterialuikit.library.material.drawable.RippleDrawable;
import com.github.shareme.gwsmaterialuikit.library.material.drawable.ToolbarRippleDrawable;
import com.github.shareme.gwsmaterialuikit.library.material.util.ViewUtil;


public final class RippleManager implements View.OnClickListener{

	private View.OnClickListener mClickListener;
    private boolean mClickScheduled = false;
		
	public RippleManager(){}

    /**
     * Should be called in the construction method of view to create a RippleDrawable.
     * @param v
     * @param context
     * @param attrs
     * @param defStyleAttr
     * @param defStyleRes
     */
	public void onCreate(View v, Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
		if(v.isInEditMode())
			return;

		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RippleView, defStyleAttr, defStyleRes);
        int rippleStyle = a.getResourceId(R.styleable.RippleView_rd_style, 0);
		RippleDrawable drawable = null;

		if(rippleStyle != 0)
			drawable = new RippleDrawable.Builder(context, rippleStyle).backgroundDrawable(getBackground(v)).build();
		else{
			boolean rippleEnable = a.getBoolean(R.styleable.RippleView_rd_enable, false);
			if(rippleEnable)
				drawable = new RippleDrawable.Builder(context, attrs, defStyleAttr, defStyleRes).backgroundDrawable(getBackground(v)).build();
		}

		a.recycle();

		if(drawable != null)
            ViewUtil.setBackground(v, drawable);
	}

    private Drawable getBackground(View v){
        Drawable background = v.getBackground();
        if(background == null)
            return null;

        if(background instanceof RippleDrawable)
            return ((RippleDrawable)background).getBackgroundDrawable();

        return background;
    }
		
	public void setOnClickListener(View.OnClickListener l) {
		mClickListener = l;
	}

	public boolean onTouchEvent(View v, MotionEvent event){
		Drawable background = v.getBackground();
        return background != null && background instanceof RippleDrawable && ((RippleDrawable) background).onTouch(v, event);
    }
	
	@Override
	public void onClick(View v) {
		Drawable background = v.getBackground();
		long delay = 0;

		if(background != null) {
			if (background instanceof RippleDrawable)
				delay = ((RippleDrawable) background).getClickDelayTime();
			else if (background instanceof ToolbarRippleDrawable)
				delay = ((ToolbarRippleDrawable) background).getClickDelayTime();
		}
			
		if(delay > 0 && v.getHandler() != null && !mClickScheduled) {
            mClickScheduled = true;
            v.getHandler().postDelayed(new ClickRunnable(v), delay);
        }
		else
			dispatchClickEvent(v);
	}

	private void dispatchClickEvent(View v){
		if(mClickListener != null)
			mClickListener.onClick(v);
	}

    /**
     * Cancel the ripple effect of this view and all of it's children.
     * @param v
     */
	public static void cancelRipple(View v){
		Drawable background = v.getBackground();
		if(background instanceof RippleDrawable)
			((RippleDrawable)background).cancel();
		else if(background instanceof ToolbarRippleDrawable)
			((ToolbarRippleDrawable)background).cancel();
		
		if(v instanceof ViewGroup){
			ViewGroup vg = (ViewGroup)v;
			for(int i = 0, count = vg.getChildCount(); i < count; i++)
				RippleManager.cancelRipple(vg.getChildAt(i));
		}
	}

	class ClickRunnable implements Runnable{
		View mView;

		public ClickRunnable(View v){
			mView = v;
		}

		@Override
		public void run() {
			mClickScheduled = false;
			dispatchClickEvent(mView);
		}
	}
	
}
