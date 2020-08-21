package com.rebrotesolution.smzr_android.viewModels.login

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.LoginResultCallBacks
import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.network.repository.PersonaRepository
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository
import com.rebrotesolution.smzr_android.utils.ApiException
import com.rebrotesolution.smzr_android.utils.Coroutines
import com.rebrotesolution.smzr_android.utils.NoInternetException
import es.dmoral.toasty.Toasty
import kotlin.math.log

class LoginViewModel(
    private val listener: LoginResultCallBacks,
    private val userRepo: UsuarioRepository,
    private val personaRepo: PersonaRepository
) : ViewModel() {

    private val usuario: Usuario

    init {
        usuario = Usuario(username = "", password = "", id_usuario = 0, token = "")
    }

    val usernameTextWatcher: TextWatcher
    get() = object:TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
              usuario.username = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        }

    val passwordTextWatcher: TextWatcher
    get() = object:TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            usuario.password = p0.toString()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    }

    fun onLoginClicked(v: View){
        listener.onStarted()
        if(usuario.isDataComplete){
           Coroutines.main {
               try {
                   val authResponse =  userRepo.userLogin(usuario.username, usuario.password)
                   authResponse.persona?.let {
                       listener.onSuccess(it.usuario!!)
                       userRepo.saveSesion(it.usuario!!)
                       personaRepo.savePersonaInLocal(it)
                       return@main
                   }
                   listener.onError(authResponse.message!!)
               }catch (e:ApiException){
                   listener.onError(e.message!!)
               }catch (e: NoInternetException){
                   listener.onError(e.message!!)
               }
           }
        }else{
            listener.onError("Complete los datos para continuar" )
        }
    }

    fun onRegisterClicked(v:View){
        listener.onRegister()
    }

    fun getLoggedInUser() = userRepo.getUsuario()
}