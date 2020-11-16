package com.mandiri.fahri.Utils

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide

internal fun ImageView.ImageLoad(url: String?) {

        Log.i("TAGED","http://image.tmdb.org/t/p/w185"+url)

       Glide.with(this.context).load("https://image.tmdb.org/t/p/w185"+url).centerCrop().into(this)

}