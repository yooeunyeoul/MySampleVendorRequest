package com.example.mysamplevendorrequest.model

import com.example.mysamplevendorrequest.entity.Vendor
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class VendorListResponse(
    @SerializedName("data") var data: Data
) : BaseResponse() {

    data class Data(@SerializedName("list") var list: List<Vendor> = ArrayList())



}open class BaseResponse(
    @SerializedName("code") var code: Int = 0
    , @SerializedName("msg") var msg: String = "오류가 발생하였습니다. 잠시 후 다시 시도해주세요."
)