package com.example.mysamplevendorrequest.base

import android.app.Application
import android.content.Context
import android.graphics.Typeface
import io.reactivex.plugins.RxJavaPlugins
import org.koin.android.ext.android.startKoin

class App : Application() {

    companion object {
        private lateinit var instance: App
        fun getGlobalApplicationContext(): App {
            return instance
        }
    }

    override fun onCreate() {
        instance = this
        super.onCreate()


//        startKoin(this, diModule)


    }

}