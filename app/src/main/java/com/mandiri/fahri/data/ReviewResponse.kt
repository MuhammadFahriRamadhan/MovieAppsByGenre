package com.mandiri.fahri.data

import com.google.gson.annotations.SerializedName

data class ReviewResponse (
    @SerializedName("id")
    val id :Long = -1L,
    @SerializedName("page")
    val page : Int = 0,
    @SerializedName("results")
    val results : List<ReviewResult>
)


data class  ReviewResult(
    @SerializedName("author")
    val author :String? =null,
    @SerializedName("content")
    val content : String? = null
)