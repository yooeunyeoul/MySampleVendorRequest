package com.example.mysamplevendorrequest.module

import com.example.mysamplevendorrequest.model.datamodel.VendorDataModel
import com.example.mysamplevendorrequest.model.impl.VendorDataModelImpl
import com.example.mysamplevendorrequest.network.RestAPI
import com.example.mysamplevendorrequest.network.service.VendorService
import com.example.mysamplevendorrequest.view.VendorAdapter
import com.example.mysamplevendorrequest.viewModel.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

var retrofitModule = module {
    single<VendorService> { RestAPI.getRetrofit().create(VendorService::class.java) }
}

var dataModelModule = module {
    factory<VendorDataModel> {
        VendorDataModelImpl(get())
    }
}

var viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

var adapterModule = module {
    factory { VendorAdapter() }
}


var diModule = listOf(retrofitModule, dataModelModule, viewModelModule, adapterModule)
