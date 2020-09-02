package com.rebrotesolution.smzr_android.viewModels.factory

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebrotesolution.smzr_android.interfaces.ButtonAcceptHandler
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.EmpezarFormularioViewModel
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MalestarForm1ViewModel

class EmpezarFormularioViewModelFactory(
    private var listener: ButtonAcceptHandler
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EmpezarFormularioViewModel(listener) as T
    }
}