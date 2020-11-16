package com.mandiri.fahri.domain

import com.mandiri.fahri.domain.common.Usecase
import com.mandiri.fahri.domain.executor.JobExecutor
import com.mandiri.fahri.domain.executor.UIThread
import io.reactivex.Single

class HomeUsecase(
    private val repository: HomeRepository,
    executor: JobExecutor,
    thread: UIThread
) : Usecase<HomeEntity, HomeParam>(executor, thread) {

    override fun buildUsecaseObservable(params: HomeParam): Single<HomeEntity> =
        repository.discoverMovie(params)
}