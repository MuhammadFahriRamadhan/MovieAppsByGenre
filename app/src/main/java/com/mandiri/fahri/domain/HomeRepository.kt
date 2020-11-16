package com.mandiri.fahri.domain

import io.reactivex.Single

interface HomeRepository {

    fun discoverMovie(param: HomeParam): Single<HomeEntity>
    fun getGenreMovie() : Single<GenreEntity>
    fun getTrailer(trailerParam: TrailerParam) : Single<TrailerEntity>
    fun getReview(reviewParam: ReviewParam) : Single<ReviewEntity>
 }