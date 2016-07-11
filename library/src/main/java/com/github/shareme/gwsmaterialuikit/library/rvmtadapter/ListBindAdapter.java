/*
 * Copyright 2015 yqritc
 * Modifications Copyright(C) 2016 Fred Grott(GrottWorkShop)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.github.shareme.gwsmaterialuikit.library.rvmtadapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adapter class for managing data binders when the order of binder is in sequence
 *
 * Created by yqritc on 2015/03/19.
 */
@SuppressWarnings("unused")
public class ListBindAdapter extends DataBindAdapter {

    private List<DataBinder> mBinderList = new ArrayList<>();

    @Override
    public int getItemCount() {
        int itemCount = 0;
        for (int i = 0, size = mBinderList.size(); i < size; i++) {
            DataBinder binder = mBinderList.get(i);
            itemCount += binder.getItemCount();
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        int itemCount = 0;
        for (int viewType = 0, size = mBinderList.size(); viewType < size; viewType++) {
            itemCount += mBinderList.get(viewType).getItemCount();
            if (position < itemCount) {
                return viewType;
            }
        }
        throw new IllegalArgumentException("arg position is invalid");
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends DataBinder> T getDataBinder(int viewType) {
        return (T) mBinderList.get(viewType);
    }

    @Override
    public int getPosition(DataBinder binder, int binderPosition) {
        int viewType = mBinderList.indexOf(binder);
        if (viewType < 0) {
            throw new IllegalStateException("binder does not exist in adapter");
        }

        int position = binderPosition;
        for (int i = 0; i < viewType; i++) {
            position += mBinderList.get(i).getItemCount();
        }

        return position;
    }

    @Override
    public int getBinderPosition(int position) {
        int binderItemCount;
        for (int i = 0, size = mBinderList.size(); i < size; i++) {
            binderItemCount = mBinderList.get(i).getItemCount();
            if (position - binderItemCount < 0) {
                break;
            }
            position -= binderItemCount;
        }
        return position;
    }

    @Override
    public void notifyBinderItemRangeChanged(DataBinder binder, int positionStart, int itemCount) {
        notifyItemRangeChanged(getPosition(binder, positionStart), itemCount);
    }

    @Override
    public void notifyBinderItemRangeInserted(DataBinder binder, int positionStart, int itemCount) {
        notifyItemRangeInserted(getPosition(binder, positionStart), itemCount);
    }

    @Override
    public void notifyBinderItemRangeRemoved(DataBinder binder, int positionStart, int itemCount) {
        notifyItemRangeRemoved(getPosition(binder, positionStart), itemCount);
    }

    public List<DataBinder> getBinderList() {
        return mBinderList;
    }

    public void addBinder(DataBinder binder) {
        mBinderList.add(binder);
    }

    public void addAllBinder(List<DataBinder> dataSet) {
        mBinderList.addAll(dataSet);
    }

    public void addAllBinder(DataBinder... dataSet) {
        mBinderList.addAll(Arrays.asList(dataSet));
    }
}
