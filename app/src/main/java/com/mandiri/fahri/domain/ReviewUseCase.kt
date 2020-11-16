package com.mandiri.fahri.domain

import com.mandiri.fahri.domain.common.Usecase
import com.mandiri.fahri.domain.executor.JobExecutor
import com.mandiri.fahri.domain.executor.UIThread
import io.reactivex.Single

class ReviewUseCase (private val repository: HomeRepository,
                     executor: JobExecutor,
                     thread: UIThread
) : Usecase<ReviewEntity, ReviewParam>(executor,thread) {
    override fun buildUsecaseObservable(param: ReviewParam): Single<ReviewEntity> = repository.getReview(param)
}