package com.mandiri.fahri.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class ReviewEntity(
    val id :Long = -1L,
    val page : Int = 0,
    val results : MutableList<ReviewResults>
) {

    @Parcelize
    data class ReviewResults(
        val author : String? = null,
        val content : String? = null
    ) : Parcelable
}