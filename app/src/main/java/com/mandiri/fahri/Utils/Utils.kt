package com.mandiri.fahri.Utils

import android.content.res.Resources
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.mandiri.fahri.R
import dagger.android.support.DaggerAppCompatActivity

object Utils {

    fun snackbar(appCompatActivity: DaggerAppCompatActivity, layout: View, msg: String, icon: Int, color : Boolean) {
        val snackbar = Snackbar.make(layout, msg, Snackbar.LENGTH_LONG)
        val snackbarLayout = snackbar.view
        val textView = snackbarLayout.findViewById(R.id.snackbar_text) as TextView
        if (color){
            snackbarLayout.setBackgroundColor(Color.parseColor("#00c853"))
            textView.setTextColor(Color.WHITE)
        }else{
            snackbarLayout.setBackgroundColor(Color.parseColor("#d32f2f"))
            textView.setTextColor(Color.WHITE)
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0)
        textView.compoundDrawablePadding = dpToPx(12f, appCompatActivity.resources).toInt()
        textView.gravity = Gravity.CENTER
        snackbar.show()
    }
    private fun dpToPx(value: Float, resources: Resources): Float {
        return value * resources.displayMetrics.density + 0.5f
    }
}