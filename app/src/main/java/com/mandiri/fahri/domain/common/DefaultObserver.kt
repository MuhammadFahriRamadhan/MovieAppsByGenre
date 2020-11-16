package com.mandiri.fahri.domain.common

import io.reactivex.observers.DisposableSingleObserver

abstract class DefaultObserver<T> : DisposableSingleObserver<T>()  {
    override fun onSuccess(entity: T) {}
    override fun onError(exception: Throwable) {}
}