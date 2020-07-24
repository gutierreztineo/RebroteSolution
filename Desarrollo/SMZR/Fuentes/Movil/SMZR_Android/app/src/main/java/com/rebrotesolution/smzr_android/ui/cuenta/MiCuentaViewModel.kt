package com.rebrotesolution.smzr_android.ui.cuenta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MiCuentaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Mi cuenta Fragment"
    }
    val text: LiveData<String> = _text
}
