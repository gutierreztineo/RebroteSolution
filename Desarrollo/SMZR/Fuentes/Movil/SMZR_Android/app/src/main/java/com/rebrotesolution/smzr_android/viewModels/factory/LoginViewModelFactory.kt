package com.rebrotesolution.smzr_android.viewModels.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebrotesolution.smzr_android.interfaces.LoginResultCallBacks
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository
import com.rebrotesolution.smzr_android.viewModels.login.LoginViewModel

class LoginViewModelFactory(
    private val listener: LoginResultCallBacks,
    private val repository: UsuarioRepository
) : ViewModelProvider.NewInstanceFactory() {

    override  fun <T: ViewModel?> create( modelClass: Class<T>):T {
        return LoginViewModel(listener,repository) as T
    }
}