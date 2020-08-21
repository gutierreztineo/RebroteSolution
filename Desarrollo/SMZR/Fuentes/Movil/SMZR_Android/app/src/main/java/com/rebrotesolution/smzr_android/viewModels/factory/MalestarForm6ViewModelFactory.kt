package com.rebrotesolution.smzr_android.viewModels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm6ViewModel

class MalestarForm6ViewModelFactory (
    private val listener: FormularioMalestarResultCallBacks
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MalestarForm6ViewModel(listener) as T
    }
}