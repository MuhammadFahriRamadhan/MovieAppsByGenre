package com.mandiri.fahri.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class HomeEntity(
    val page: Long,
    val totalPages: Long,
    val results: MutableList<Results>) {
    @Parcelize
    data class Results(
        val id : Int,
        val title: String,
        val overview: String,
        val poster_path :String,
        val release_date :String,
        val popularity : String,
        val vote_average :String
    ) : Parcelable
}