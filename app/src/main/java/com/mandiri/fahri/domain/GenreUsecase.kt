package com.mandiri.fahri.domain

import com.mandiri.fahri.domain.common.Usecasegenres
import com.mandiri.fahri.domain.executor.JobExecutor
import com.mandiri.fahri.domain.executor.UIThread
import io.reactivex.Single

class GenreUsecase( private val repository: HomeRepository,executor: JobExecutor,
                   thread: UIThread
) : Usecasegenres<GenreEntity>(executor, thread) {
    override fun buildUsecasegenresObservable(): Single<GenreEntity> = repository.getGenreMovie()


}