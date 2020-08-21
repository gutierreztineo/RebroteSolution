package com.rebrotesolution.smzr_android.viewModels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm7ViewModel

class MalestarForm7ViewModelFactory(
    private val listener: FormularioMalestarResultCallBacks
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MalestarForm7ViewModel(listener) as T
    }
}