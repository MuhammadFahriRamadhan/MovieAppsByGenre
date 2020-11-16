package com.mandiri.fahri.presentation

import com.mandiri.fahri.domain.*
import com.mandiri.fahri.domain.common.DefaultObserver

class HomeMoviePresenter(private val view : HomeMovieView,private val usecase : HomeUsecase,private val usecasegenre : GenreUsecase) {

    fun discoverMovie(idgenres : String,page: Long){
        view.onShowLoading()
        usecase.execute(DiscoverMovieUsecase(), HomeParam(idgenres,page))
    }

    fun getGenresMovie(){
        usecasegenre.executegenres(discoverGenresMovie())
    }



    fun loadMore(idgenres : String,page: Long) {
        usecase.execute(
            LoadMoreUsecase(),
            HomeParam(idgenres,page)
        )
    }

    fun onDetach() {
        usecase.dispose()
    }


    inner class discoverGenresMovie : DefaultObserver<GenreEntity>(){
        override fun onSuccess(entity: GenreEntity) {
            view.onSucessGenre(entity)
        }
        override fun onError(exception: Throwable) {
            view.onError(exception)
        }
    }

    inner class DiscoverMovieUsecase : DefaultObserver<HomeEntity>() {

        override fun onSuccess(entity: HomeEntity) {
            view.onHideLoading()
            view.onSuccess(entity)
        }

        override fun onError(exception: Throwable) {
            view.onHideLoading()
            view.onError(exception)
        }
    }

    inner class LoadMoreUsecase : DefaultObserver<HomeEntity>() {

        override fun onSuccess(entity: HomeEntity) {
            view.onPaginationSuccess(entity)
        }

        override fun onError(exception: Throwable) {
            view.onPaginationError(exception)
        }
    }

}