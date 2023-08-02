package com.doaasaif.domain.entity

import android.os.Parcel
import android.os.Parcelable

/*
@Parcelize
*/
data class LatestResponse(
    val base: String,
    val date: String,
    val rates: Map<String, Double>,
    val success: Boolean,
    val timestamp: Long
)/*: Parcelable{
    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }
}*/