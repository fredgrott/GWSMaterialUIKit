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

import java.util.HashMap;
import java.util.Map;

/**
 * Adapter class for managing data binders by mapping enum view type and data binder
 *
 * Created by yqritc on 2015/03/25.
 */
@SuppressWarnings("unused")
public abstract class EnumMapBindAdapter<E extends Enum<E>> extends DataBindAdapter {

    private Map<E, DataBinder> mBinderMap = new HashMap<>();

    @Override
    public int getItemCount() {
        int itemCount = 0;
        for (DataBinder binder : mBinderMap.values()) {
            itemCount += binder.getItemCount();
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        return getEnumFromPosition(position).ordinal();
    }

    @Override
    public <T extends DataBinder> T getDataBinder(int viewType) {
        return getDataBinder(getEnumFromOrdinal(viewType));
    }

    @Override
    public int getPosition(DataBinder binder, int binderPosition) {
        E targetViewType = getEnumFromBinder(binder);
        for (int i = 0, count = getItemCount(); i < count; i++) {
            if (targetViewType == getEnumFromPosition(i)) {
                binderPosition--;
                if (binderPosition < 0) {
                    return i;
                }
            }
        }
        return getItemCount();
    }

    @Override
    public int getBinderPosition(int position) {
        E targetViewType = getEnumFromPosition(position);
        int binderPosition = -1;
        for (int i = 0; i <= position; i++) {
            if (targetViewType == getEnumFromPosition(i)) {
                binderPosition++;
            }
        }

        if (binderPosition == -1) {
            throw new IllegalArgumentException("Invalid Argument");
        }
        return binderPosition;
    }

    @Override
    public void notifyBinderItemRangeChanged(DataBinder binder, int positionStart, int itemCount) {
        for (int i = positionStart; i <= itemCount; i++) {
            notifyItemChanged(getPosition(binder, i));
        }
    }

    @Override
    public void notifyBinderItemRangeInserted(DataBinder binder, int positionStart, int itemCount) {
        for (int i = positionStart; i <= itemCount; i++) {
            notifyItemInserted(getPosition(binder, i));
        }
    }

    @Override
    public void notifyBinderItemRangeRemoved(DataBinder binder, int positionStart, int itemCount) {
        for (int i = positionStart; i <= itemCount; i++) {
            notifyItemRemoved(getPosition(binder, i));
        }
    }

    public abstract E getEnumFromPosition(int position);

    public abstract E getEnumFromOrdinal(int ordinal);

    public E getEnumFromBinder(DataBinder binder) {
        for (Map.Entry<E, DataBinder> entry : mBinderMap.entrySet()) {
            if (entry.getValue().equals(binder)) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Invalid Data Binder");
    }

    @SuppressWarnings("unchecked")
    public <T extends DataBinder> T getDataBinder(E e) {
        return (T) mBinderMap.get(e);
    }

    public Map<E, DataBinder> getBinderMap() {
        return mBinderMap;
    }

    public void putBinder(E e, DataBinder binder) {
        mBinderMap.put(e, binder);
    }
}
