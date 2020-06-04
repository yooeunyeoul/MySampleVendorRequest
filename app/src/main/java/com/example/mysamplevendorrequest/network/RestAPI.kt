package com.example.mysamplevendorrequest.network

import com.example.mysamplevendorrequest.Utils.BuildConfig
import com.example.mysamplevendorrequest.Utils.UnsafeOkHttpClient
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object RestAPI  {


    //    val url_v1 = "https://new-service.lab-manager.com/";
//    val url_v1 = "https://dev-service.lab-manager.com/"
    val url_v1 = BuildConfig.URL
    val BASIC_TOKEN = BuildConfig.TOKEN


    private val httpClient = UnsafeOkHttpClient.unsafeOkHttpClient
        .addInterceptor {chain ->
            val original = chain.request()
            var request = original.newBuilder()
                .header("X-platform", "mobile-android")
                .header("X-version", BuildConfig.VERSION_NAME)

                request = request.header("Accept-Language", "ko")


            request = request.method(original.method(), original.body())
            chain.proceed(request.build())
        }

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(url_v1)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    fun getRetrofit() = retrofit

}