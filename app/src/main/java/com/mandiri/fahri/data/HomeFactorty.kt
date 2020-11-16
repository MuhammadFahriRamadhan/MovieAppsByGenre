package com.mandiri.fahri.data

import com.mandiri.fahri.BuildConfig
import io.reactivex.Single

class HomeFactorty(private val dataSource: HomeDataSource) {

    fun discoveryMovie(idgenres : String,page : Long) : Single<HomeResponse> = dataSource.discoverMovie(genres = idgenres ,apikey = BuildConfig.API_KEY,page = page)
    fun getGenreMovie() : Single<GenreResponse> =  dataSource.getGenreMovie(apikey = BuildConfig.API_KEY)
    fun getTrailer(id : Int) : Single<TrailerResponse> = dataSource.getTrailer(id = id,apikey = BuildConfig.API_KEY)
    fun getReview(id : Int) : Single<ReviewResponse> = dataSource.getReviews(id = id,apikey = BuildConfig.API_KEY)

}