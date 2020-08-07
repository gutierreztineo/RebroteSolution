package com.rebrotesolution.smzr_android.viewModels.register

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.models.Usuario

class DatosCuentaViewModel(
    private val listener: RegisterResultCallBacks
) : ViewModel() {

    private val usuario: Usuario
    init {
        usuario = Usuario(username ="", password ="", token = "", id_usuario = 0)
    }

    private var confirmpass: String = ""
    private var passvalid : Boolean = false

    val usernameTextWatcher: TextWatcher
    get() = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
           usuario.setUsername(p0.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    val passwordTextWatcher: TextWatcher
    get() = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            usuario.setPassword(p0.toString())
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    val passConfirmTextWatcher: TextWatcher
    get() = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            confirmpass = p0.toString()
            passvalid = confirmpass == usuario.getPassword()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    fun onNextClicked(v: View){
        if(usuario.isDataComplete){
            if(usuario.isDataValid){
                if(passvalid){
                    var data : Map<String,String> = mapOf("username" to usuario.getUsername(), "password" to usuario.getPassword())
                    listener.valid(data)
                }else{
                    listener.invalid("Las contraseñas no coinciden")
                }
            }else{
                listener.invalid("La contraseña debe tener como mínimo 5 caracteres")
            }
        }else{
            listener.invalid("Complete los datos para continuar")
        }
    }

}
