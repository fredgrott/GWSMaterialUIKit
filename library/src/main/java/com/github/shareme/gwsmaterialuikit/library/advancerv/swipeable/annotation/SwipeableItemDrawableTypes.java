/*
 *    Copyright (C) 2015 Haruki Hasegawa
 *    Modifications Copyright(C) 2016 Fred Grott(GrottWorkShop)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.shareme.gwsmaterialuikit.library.advancerv.swipeable.annotation;

import android.support.annotation.IntDef;

import com.github.shareme.gwsmaterialuikit.library.advancerv.swipeable.SwipeableItemConstants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@IntDef(flag = false, value = {
        SwipeableItemConstants.DRAWABLE_SWIPE_NEUTRAL_BACKGROUND,
        SwipeableItemConstants.DRAWABLE_SWIPE_LEFT_BACKGROUND,
        SwipeableItemConstants.DRAWABLE_SWIPE_UP_BACKGROUND,
        SwipeableItemConstants.DRAWABLE_SWIPE_RIGHT_BACKGROUND,
        SwipeableItemConstants.DRAWABLE_SWIPE_DOWN_BACKGROUND,
})
@Retention(RetentionPolicy.SOURCE)
public @interface SwipeableItemDrawableTypes {
}
