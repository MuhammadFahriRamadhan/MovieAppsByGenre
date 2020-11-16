package com.mandiri.fahri.domain

data class GenreEntity(val genres : MutableList<GenresResult>) {

    data class GenresResult(
        val id : Long,
        val name : String

    ){
        override fun toString(): String = name
    }


}