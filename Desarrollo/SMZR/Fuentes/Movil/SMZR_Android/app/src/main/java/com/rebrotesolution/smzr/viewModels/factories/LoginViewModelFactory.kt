package com.rebrotesolution.smzr.viewModels.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebrotesolution.smzr.interfaces.LoginResultCallBacks
import com.rebrotesolution.smzr.viewModels.login.LoginViewModel

class LoginViewModelFactory (
    private val listener: LoginResultCallBacks
) : ViewModelProvider.NewInstanceFactory() {

    override  fun <T: ViewModel?> create( modelClass: Class<T>):T {
        return LoginViewModel(listener) as T
    }
}