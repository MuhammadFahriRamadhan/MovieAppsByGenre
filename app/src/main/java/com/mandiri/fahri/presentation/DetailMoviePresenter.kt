package com.mandiri.fahri.presentation

import com.mandiri.fahri.domain.*
import com.mandiri.fahri.domain.common.DefaultObserver

class DetailMoviePresenter(private val view: DetailMovieView,private val trailerusecase : TrailerUseCase,private val reviewUseCase: ReviewUseCase) {


    fun getReview(id : Int){
        reviewUseCase.execute(discoverygetreview(), ReviewParam(id))
    }
    inner class discoverygetreview : DefaultObserver<ReviewEntity>(){
        override fun onSuccess(entity: ReviewEntity) {
            view.onSucessReview(entity)
        }
        override fun onError(exception: Throwable) {
            view.onError(exception)
        }
    }
    fun getTrailer(id : Int){
        trailerusecase.execute(discoverygetTrailer(), TrailerParam(id))
    }
    inner class discoverygetTrailer : DefaultObserver<TrailerEntity>(){

        override fun onSuccess(entity: TrailerEntity) {
            view.onSucessTrailer(entity)
        }
        override fun onError(exception: Throwable) {
            view.onError(exception)
        }
    }
}