package com.mandiri.fahri.presentation

import com.mandiri.fahri.domain.GenreEntity
import com.mandiri.fahri.domain.HomeEntity

interface HomeMovieView {
    fun onShowLoading()
    fun onHideLoading()
    fun onSuccess(entity: HomeEntity)
    fun onSucessGenre(entity: GenreEntity)

    fun onError(error: Throwable)
    fun onPaginationSuccess(entity: HomeEntity)
    fun onPaginationError(error: Throwable)
}