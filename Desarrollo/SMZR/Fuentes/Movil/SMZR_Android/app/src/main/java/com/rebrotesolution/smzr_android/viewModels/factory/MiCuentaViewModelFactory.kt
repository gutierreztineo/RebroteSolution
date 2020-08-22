package com.rebrotesolution.smzr_android.viewModels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebrotesolution.smzr_android.interfaces.ApiResultCallBacks
import com.rebrotesolution.smzr_android.network.repository.PersonaRepository
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository
import com.rebrotesolution.smzr_android.viewModels.cuenta.MiCuentaViewModel

class MiCuentaViewModelFactory(
    private val repositoryPersona: PersonaRepository,
    private val repositoryUser: UsuarioRepository,
    private val listener: ApiResultCallBacks
): ViewModelProvider.NewInstanceFactory() {
    override  fun <T: ViewModel?> create(modelClass: Class<T>):T {
        return MiCuentaViewModel(repositoryPersona,repositoryUser,listener) as T
    }
}