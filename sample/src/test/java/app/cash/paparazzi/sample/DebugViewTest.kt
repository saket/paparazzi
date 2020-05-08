package app.cash.paparazzi.sample

import android.content.Context
import android.graphics.Color
import android.graphics.Color.LTGRAY
import android.view.Gravity
import android.view.Gravity.CENTER
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.FrameLayout.LayoutParams
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import app.cash.paparazzi.Paparazzi
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters.JVM

@FixMethodOrder(JVM)
class DebugViewTest {
  @get:Rule
  var paparazzi = Paparazzi()

  private val context: Context
    get() = paparazzi.context

  @Test
  fun vectorDrawable() {
    val textView = AppCompatTextView(context).apply {
      text = "20.12%"
      layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
      setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
      val drawable = AppCompatResources.getDrawable(context, R.drawable.arrow_up)
      setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
    }
    paparazzi.snapshot(textView, "vector test")
  }

  @Test
  fun fonts() {
    val textView = AppCompatTextView(context).apply {
      layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
      gravity = CENTER
      text = "PAY ME"
      textSize = 80f
      setTextColor(0xFF9013FE.toInt())
      typeface = ResourcesCompat.getFont(context, R.font.beer_money)
      setBackgroundColor(0xFFE5E5E5.toInt())
    }
    paparazzi.snapshot(textView, "fonts test")
  }

  @Test
  fun inflateVectorDrawable() {
    val root = paparazzi.inflate<FrameLayout>(R.layout.debug)
    paparazzi.snapshot(root, "inflate vector test")
  }
}