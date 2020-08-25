package com.rebrotesolution.smzr_android.viewModels.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository
import com.rebrotesolution.smzr_android.viewModels.register.DatosCuentaViewModel

class DatosCuentaViewModelFactory(
    private val listener : RegisterResultCallBacks,
    private val userRepo: UsuarioRepository,
    private val shared: SharedPreferences
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DatosCuentaViewModel(listener,userRepo,shared) as T
    }
}