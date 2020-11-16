package com.mandiri.fahri.domain.common

import com.mandiri.fahri.domain.executor.PostExecutionThread
import com.mandiri.fahri.domain.executor.ThreadExcutor
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

abstract class Usecasegenres<T>(private val threadExecutor: ThreadExcutor,
                        private val postExecutionThread : PostExecutionThread){
    private val disposable = CompositeDisposable()

    protected  abstract fun buildUsecasegenresObservable() : Single<T>


    fun executegenres(observer: DefaultObserver<T>){
        buildUsecasegenresObservable().subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.scheduler)
            .subscribeWith(observer)
            .addTo(disposable)
    }

    fun dispose(){
        disposable.clear()
    }
}