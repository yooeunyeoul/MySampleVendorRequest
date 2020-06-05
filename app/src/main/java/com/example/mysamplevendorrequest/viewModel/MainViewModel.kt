package com.example.mysamplevendorrequest.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysamplevendorrequest.model.VendorListResponse
import com.example.mysamplevendorrequest.model.datamodel.VendorDataModel
import com.example.mysamplevendorrequest.network.RestAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val vendorDataModel: VendorDataModel) : ViewModel() {

    val responseLiveData = MutableLiveData<VendorListResponse>()

    private val compositeDisposable = CompositeDisposable()

    fun getVendorList(keyword: String) {
        addDisposable(vendorDataModel.getVendorList(keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe { it ->
                Log.e("TAG", "getVendorList: $it")
                responseLiveData.postValue(it)
            })
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


}
