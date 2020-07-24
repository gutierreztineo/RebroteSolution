package com.rebrotesolution.smzr_android.viewModels.prevencion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PrevencionCovidViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is prevencion Covid Fragment"
    }
    val text: LiveData<String> = _text
}
