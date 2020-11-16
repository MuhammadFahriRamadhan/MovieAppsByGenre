package com.mandiri.fahri.data

import com.google.gson.annotations.SerializedName

data class TrailerResponse (
            @SerializedName("id")
            val id :Int = 0,
            @SerializedName("results")
            val results : List<TrailerResult>
            )


data class  TrailerResult(
            @SerializedName("key")
            val key :String? =null,
            @SerializedName("name")
            val name : String? = null
            )