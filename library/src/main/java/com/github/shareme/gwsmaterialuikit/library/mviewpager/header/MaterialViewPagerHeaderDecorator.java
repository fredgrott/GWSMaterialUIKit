/*
  Copyright 2015 florent37, Inc.
  Modifications Copyright(C) 2016 Fred Grott(GrottWorkShop)

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

 */
package com.github.shareme.gwsmaterialuikit.library.mviewpager.header;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.shareme.gwsmaterialuikit.library.mviewpager.MaterialViewPagerAnimator;
import com.github.shareme.gwsmaterialuikit.library.mviewpager.MaterialViewPagerHelper;
import com.github.shareme.gwsmaterialuikit.library.mviewpager.Utils;


/**
 * Created by florentchampigny on 27/05/2016.
 */
@SuppressWarnings("unused")
public class MaterialViewPagerHeaderDecorator extends RecyclerView.ItemDecoration {

    boolean registered = false;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        final RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);
        final Context context = recyclerView.getContext();

        if(!registered) {
            MaterialViewPagerHelper.registerRecyclerView(context, recyclerView);
            registered = true;
        }

        int headerCells = 1;

        //don't work with stagged
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager = (GridLayoutManager)layoutManager;
            headerCells = gridLayoutManager.getSpanCount();
        }

        MaterialViewPagerAnimator animator = MaterialViewPagerHelper.getAnimator(context);
        if (animator != null) {
            if (holder.getAdapterPosition() < headerCells) {
                outRect.top = Math.round(Utils.dpToPx(animator.getHeaderHeight() + 10, context));
            }
        }
    }
}
