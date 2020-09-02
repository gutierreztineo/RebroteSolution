package com.rebrotesolution.smzr_android.interfaces

import com.rebrotesolution.smzr_android.models.MyLatLng

interface IOnLoadLocationListener {

    fun onLocationLoadSuccess(latLngs: List<MyLatLng>)
    fun onLocationLoadFailed(message:String)
}