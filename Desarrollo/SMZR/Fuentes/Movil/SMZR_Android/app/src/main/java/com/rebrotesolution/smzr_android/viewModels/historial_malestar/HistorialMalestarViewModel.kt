package com.rebrotesolution.smzr_android.viewModels.historial_malestar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HistorialMalestarViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Historial Malestar Fragment"
    }
    val text: LiveData<String> = _text
}
