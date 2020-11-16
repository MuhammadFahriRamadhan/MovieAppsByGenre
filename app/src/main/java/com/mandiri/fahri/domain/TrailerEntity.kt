package com.mandiri.fahri.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class TrailerEntity(
    val id :Int = 0,
    val results : MutableList<TrailerResults>
) {

    @Parcelize
    data class TrailerResults(
        val key: String? = null,
        val name: String? = null
    ) : Parcelable
}