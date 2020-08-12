package com.rebrotesolution.smzr_android.viewModels.main

import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository

class MainViewModel(
    var repository: UsuarioRepository
): ViewModel() {
    fun logOut() = repository.deleteSesion()
}