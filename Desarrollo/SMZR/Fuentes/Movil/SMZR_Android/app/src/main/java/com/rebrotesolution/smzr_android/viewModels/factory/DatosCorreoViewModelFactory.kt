package com.rebrotesolution.smzr_android.viewModels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.register.DatosCorreoViewModel

class DatosCorreoViewModelFactory (
    private val listener : RegisterResultCallBacks
    ): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DatosCorreoViewModel(listener) as T
    }
}