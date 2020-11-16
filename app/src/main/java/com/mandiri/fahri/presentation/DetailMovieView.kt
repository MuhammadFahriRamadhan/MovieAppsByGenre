package com.mandiri.fahri.presentation

import com.mandiri.fahri.domain.ReviewEntity
import com.mandiri.fahri.domain.TrailerEntity

interface DetailMovieView {
    fun onSucessTrailer(entity: TrailerEntity)
    fun onSucessReview(entity: ReviewEntity)
    fun onError(error: Throwable)
}