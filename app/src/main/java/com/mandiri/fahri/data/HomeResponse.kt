package com.mandiri.fahri.data

import com.google.gson.annotations.SerializedName

data class HomeResponse (
                         @SerializedName("page")
                         val page : Long?= -1L,
                         @SerializedName("total_pages")
                         val totalPages: Long? = -1L,
                         @SerializedName("results")
                         val results : List<Result> = emptyList()
                         )

data class Result(
                    @SerializedName("id")
                    val id : Int? = 0,
                    @SerializedName("title")
                    val title : String?=null,
                    @SerializedName("overview")
                    val overview : String?=null,
                    @SerializedName("poster_path")
                    val poster_path :String?=null,
                    @SerializedName("release_date")
                    val release_date :String,
                    @SerializedName("popularity")
                    val popularity : String,
                    @SerializedName("vote_average")
                    val vote_average : String

                )