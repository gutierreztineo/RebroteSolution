package com.rebrotesolution.smzr_android.viewModels.factory

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebrotesolution.smzr_android.interfaces.LoginResultCallBacks
import com.rebrotesolution.smzr_android.network.repository.PersonaRepository
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository
import com.rebrotesolution.smzr_android.viewModels.login.LoginViewModel

class LoginViewModelFactory(
    private val listener: LoginResultCallBacks,
    private val userRepo: UsuarioRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModelProvider.NewInstanceFactory() {

    override  fun <T: ViewModel?> create( modelClass: Class<T>):T {
        return LoginViewModel(listener,userRepo,sharedPreferences) as T
    }
}