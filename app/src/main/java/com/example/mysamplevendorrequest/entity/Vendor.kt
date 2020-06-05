package com.example.mysamplevendorrequest.entity

import com.example.mysamplevendorrequest.model.VendorArea
import com.google.gson.annotations.SerializedName
import java.util.*

data class Vendor(
    @SerializedName("vendorId") val vendorId: String? = "",
    @SerializedName("vendorName") val vendorName: String? = "",
    @SerializedName("vendorType") val vendorType: String? = "",
    @SerializedName("vendorLink") val vendorLink: String? = "",
    @SerializedName("vendorDescription") val vendorDescription: String? = "",
    @SerializedName("vendorLogoUrl") val vendorLogoUrl: String? = "",
    @SerializedName("vendorMeetingNotes") val vendorMeetingNotes: String? = "",
    @SerializedName("vendorNote1") val vendorNote1: String? = "",
    @SerializedName("vendorNote2") val vendorNote2: String? = "",
    @SerializedName("vendorArea") val vendorArea: VendorArea?,
    @SerializedName("vendorContactName") val vendorContactName: String? = ""
)

