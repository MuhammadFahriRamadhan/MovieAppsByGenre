package com.mandiri.fahri.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeDataSource {
    @GET("/3/discover/movie")
    fun discoverMovie(
        @Query("with_genres")
         genres : String,
        @Query("api_key")
        apikey : String,
        @Query("page")
        page : Long
    ): Single<HomeResponse>

    @GET("/3/genre/movie/list")
    fun getGenreMovie(
        @Query("api_key")
        apikey: String
        ) : Single<GenreResponse>

    @GET("/3/movie/{movie_id}/videos")
    fun getTrailer(
        @Path("movie_id")
        id : Int,
        @Query("api_key")
        apikey: String
    ) : Single<TrailerResponse>

    @GET("/3/movie/{movie_id}/reviews")
    fun getReviews(
        @Path("movie_id")
        id : Int,
        @Query("api_key")
        apikey: String
    ) : Single<ReviewResponse>
}