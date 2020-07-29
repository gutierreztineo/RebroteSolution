package com.rebrotesolution.smzr_android.viewModels.mapa_riesgo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapaRiesgoViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is mapa riesgo Fragment"
    }
    val text: LiveData<String> = _text
}
