package com.example.mysamplevendorrequest.network.service

import com.example.mysamplevendorrequest.model.VendorListResponse
import com.example.mysamplevendorrequest.network.RestAPI
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface VendorService {

    @GET("/v1/:groupId/vendor/list")
    fun statisticsAdd(
        @Header("Authorization") token: String = RestAPI.BASIC_TOKEN,
        @Path("groupId") groupId: String = "0V7MIF",
        @Query("keyword") keyword: String?=null,
        @Query("order") order: String = "RECENT",
        @Query("orderDirection") filterType: String = "DESC",
        @Query("offset") offset: String?=null,
        @Query("length") length: String? = null,
        @Query("filter") filter: List<Any>? = null
    ): Single<VendorListResponse>
}