package com.rebrotesolution.smzr_android.ui.actualizar_malestar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActualizarMalestarViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Actualizar Malestar Fragment"
    }
    val text: LiveData<String> = _text
}
