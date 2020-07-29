package com.rebrotesolution.smzr_android.viewModels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.register.DatosPersonales3ViewModel

class DatosPersonales3ViewModelFactory (
    private val listener : RegisterResultCallBacks
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DatosPersonales3ViewModel(listener) as T
    }
}