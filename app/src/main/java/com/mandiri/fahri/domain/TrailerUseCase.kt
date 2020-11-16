package com.mandiri.fahri.domain

import com.mandiri.fahri.domain.common.Usecase
import com.mandiri.fahri.domain.executor.JobExecutor
import com.mandiri.fahri.domain.executor.UIThread
import io.reactivex.Single

class TrailerUseCase (private val repository: HomeRepository,
                      executor: JobExecutor,
                      thread: UIThread
) : Usecase<TrailerEntity,TrailerParam> (executor,thread){
    override fun buildUsecaseObservable(param: TrailerParam): Single<TrailerEntity> = repository.getTrailer(param)

}