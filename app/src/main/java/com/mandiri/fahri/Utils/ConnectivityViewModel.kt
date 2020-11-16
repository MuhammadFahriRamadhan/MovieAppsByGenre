package com.mandiri.fahri.Utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.mandiri.fahri.domain.TypeModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ConnectivityViewModel (application: Application) : AndroidViewModel(application) {
    private val disposable = CompositeDisposable()
    private val isConnected: MutableLiveData<Boolean> = MutableLiveData()
    private val connectivityTypeName: MutableLiveData<String> = MutableLiveData()
    private val typeModel: MutableLiveData<TypeModel> = MutableLiveData()


    init{
        disposable.add(
            ReactiveNetwork.observeNetworkConnectivity(application)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { connect ->
                    isConnected.postValue(connect.available())
                    typeModel.postValue(TypeModel(connect.state()))
                }

        )
    }


    fun getType() = typeModel

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}