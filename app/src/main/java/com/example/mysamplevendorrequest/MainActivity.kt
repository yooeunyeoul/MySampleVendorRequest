package com.example.mysamplevendorrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.mysamplevendorrequest.view.VendorAdapter
import com.example.mysamplevendorrequest.viewModel.MainViewModel
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val layoutId: Int = R.layout.activity_main

    private val viewModel: MainViewModel by viewModel()

    private val vendorAdapter: VendorAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initStartView()
        initDataBinding()
        initAfterBinding()

    }

    private fun initStartView() {
        resultListView.run {
            adapter = vendorAdapter
        }


    }

    private fun initDataBinding() {
        viewModel.responseLiveData.observe(this, Observer {
            it.run {
                vendorAdapter.updateListItems(this.data.list)
            }
        })
    }

    private fun initAfterBinding() {
        with(viewModel) {
            addDisposable(
                RxTextView.textChanges(keywordEditText)
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .subscribe { textChanged ->
                        viewModel.getVendorList(textChanged.toString())
                    })
        }
    }


}