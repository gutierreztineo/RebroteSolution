package com.rebrotesolution.smzr.viewModels.login

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr.interfaces.LoginResultCallBacks
import com.rebrotesolution.smzr.models.User

class LoginViewModel (
    private val listener: LoginResultCallBacks
) : ViewModel() {

    private  val user : User

    init {
        user = User(username = "", password = "")
    }

    val usernameTextWatcher: TextWatcher
    get() = object:TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
              user.setUsername(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        }

    val passwordTextWatcher: TextWatcher
    get() = object:TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            user.setPassword(p0.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

    }

    fun onLoginClicked(v: View){
        if(user.isDataValid){
            listener.onSuccess("Login success")
        }else{
            listener.onError("Login error")
        }
    }
}