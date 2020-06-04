package com.example.mysamplevendorrequest.entity

import com.google.gson.annotations.SerializedName
import java.util.*

data class Vendor(
    @SerializedName("vendorId") var vendorId: String = ""
    , @SerializedName("vendorName") var vendorName: String = ""
    , @SerializedName("vendorType") var vendorType: String = ""
)

