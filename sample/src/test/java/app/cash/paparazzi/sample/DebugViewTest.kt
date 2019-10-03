package app.cash.paparazzi.sample

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatDrawableManager
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import app.cash.paparazzi.Paparazzi
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters.JVM

@FixMethodOrder(JVM)
class DebugViewTest {
  @get:Rule
  var paparazzi = Paparazzi()

  @Test
  fun vectorDrawable() {
    AppCompatDrawableManager.preload()
    val textView = AppCompatTextView(paparazzi.context).apply {
      text = "20.12%"
      layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
      setBackgroundColor(ContextCompat.getColor(paparazzi.context, android.R.color.holo_red_dark))
      val drawable =
        AppCompatResources.getDrawable(paparazzi.context, R.drawable.arrow_up)
      setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
    }

    paparazzi.snapshot(textView, "vector test")

  }

  @Test
  fun inflateVectorDrawable() {
    val root = paparazzi.inflate<FrameLayout>(R.layout.debug)

    paparazzi.snapshot(root, "inflate vector test")
  }
}