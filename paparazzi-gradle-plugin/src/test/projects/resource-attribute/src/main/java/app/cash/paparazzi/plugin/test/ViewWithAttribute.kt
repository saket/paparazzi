/*
 * Copyright (C) 2019 Square, Inc.
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
 */
package app.cash.paparazzi.plugin.test

import android.content.Context
import android.graphics.Color.YELLOW
import android.util.TypedValue
import android.view.Gravity.CENTER
import android.view.View
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams.MATCH_PARENT

class ViewWithAttribute(context: Context) : FrameLayout(context) {
  init {
    val toolbarSize = with(TypedValue()) {
      context.theme.resolveAttribute(android.R.attr.actionBarSize, this, true)
      TypedValue.complexToDimensionPixelSize(data, context.resources.displayMetrics)
    }
    val view = View(context).also {
      it.setBackgroundColor(YELLOW)
    }
    addView(view, LayoutParams(MATCH_PARENT, toolbarSize, CENTER))
  }
}
