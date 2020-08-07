package com.rebrotesolution.smzr_android.viewModels.login

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.LoginResultCallBacks
import com.rebrotesolution.smzr_android.models.Usuario

class LoginViewModel(
    private val listener: LoginResultCallBacks

) : ViewModel() {

    private val usuario: Usuario

    init {
        usuario = Usuario(username = "", password = "", id_usuario = 0, token = "")
    }

    val usernameTextWatcher: TextWatcher
    get() = object:TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
              usuario.setUsername(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        }

    val passwordTextWatcher: TextWatcher
    get() = object:TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            usuario.setPassword(p0.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }

    fun onLoginClicked(v: View){
        if(usuario.isDataValid){
            listener.onSuccess("Login success")
        }else{
            listener.onError("Login error")
        }
    }

    fun onRegisterClicked(v:View){
        listener.onRegister()
    }
}