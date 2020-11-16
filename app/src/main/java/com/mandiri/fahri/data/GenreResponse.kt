package com.mandiri.fahri.data

import com.google.gson.annotations.SerializedName

data class GenreResponse (
            @SerializedName("genres")
                        val genres : List<Genres>
                         )


data class Genres(
        @SerializedName("id")
        val id : Long? = -1L,
        @SerializedName("name")
        val name : String? = null
)