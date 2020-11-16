package com.mandiri.fahri.domain

import com.mandiri.fahri.data.HomeFactorty
import io.reactivex.Single

class HomeRepositoryImpl (private val factory: HomeFactorty) : HomeRepository {
    override fun discoverMovie(param: HomeParam): Single<HomeEntity> =
        factory.discoveryMovie(idgenres = param.idgenres, page = param.page).map {
            HomeEntity(
                page = it.page ?: -1L,
                totalPages = it.totalPages ?: -1L,
                results = it.results?.map {
                    HomeEntity.Results(
                        id = it.id ?: 0,
                        title = it.title ?: "Untitled",
                        overview = it.overview ?: "No Description",
                        poster_path = it.poster_path ?: "no poster",
                        release_date = it.release_date ?: "no releasedate",
                        popularity =  it.popularity ?: "no popularity",
                        vote_average = it.vote_average ?: "null"
                    )
                }?.toMutableList() ?: mutableListOf()
            )
        }

    override fun getGenreMovie(): Single<GenreEntity> =
        factory.getGenreMovie().map {
            GenreEntity(
                genres = it.genres?.map {
                    GenreEntity.GenresResult(
                        id = it.id ?: -1L,
                        name = it.name ?: "No Namme"
                    )
                }?.toMutableList() ?: mutableListOf()
            )
        }

    override fun getTrailer(trailerParam: TrailerParam): Single<TrailerEntity> =
        factory.getTrailer(id = trailerParam.id).map {
            TrailerEntity(
                id = it.id ?: 0,
                results = it.results?.map {
                    TrailerEntity.TrailerResults(
                        key = it.key ?: "null",
                        name = it.name ?: "null"
                    )
                }.toMutableList() ?: mutableListOf()
            )
        }

    override fun getReview(reviewParam: ReviewParam): Single<ReviewEntity> =
        factory.getReview(id = reviewParam.id).map {
            ReviewEntity(
                id = it.id ?: -1L,
                page = it.page ?: 0,
                results = it.results?.map {
                    ReviewEntity.ReviewResults(
                        author = it.author ?: "null",
                        content = it.content ?: "null"
                    )
                }.toMutableList()

            )
        }


}