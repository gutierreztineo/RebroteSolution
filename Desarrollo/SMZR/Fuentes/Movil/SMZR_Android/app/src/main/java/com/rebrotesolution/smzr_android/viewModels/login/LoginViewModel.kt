package com.rebrotesolution.smzr_android.viewModels.login

import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.LoginResultCallBacks
import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.network.repository.PersonaRepository
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository
import com.rebrotesolution.smzr_android.utils.ApiException
import com.rebrotesolution.smzr_android.utils.ApiTimeOutException
import com.rebrotesolution.smzr_android.utils.Coroutines
import com.rebrotesolution.smzr_android.utils.NoInternetException
import es.dmoral.toasty.Toasty
import kotlin.math.log

class LoginViewModel(
    private val listener: LoginResultCallBacks,
    private val userRepo: UsuarioRepository,
    private val personaRepo: PersonaRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val usuario: Usuario

    init {
        usuario = Usuario(username = "", password = "", id_usuario = 0, token = "")
    }

    val usernameTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                usuario.username = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        }

    val passwordTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                usuario.password = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        }

    fun onLoginClicked(v: View) {
        listener.onStarted()

        val editor = sharedPreferences.edit()
        if (usuario.isDataComplete) {
            Coroutines.main {
                try {
                    val tokenResponse = userRepo.userLogin(usuario.username, usuario.password)
                    tokenResponse.token?.let {
                        editor.putString("TOKEN", it)
                        editor.apply()

                        val personaResponse = personaRepo.consultaDatosPersonaAPI(it)
                        personaResponse.data?.let { persona ->
                            editor.putInt("ID", persona.id!!)
                            editor.apply()
                            personaRepo.savePersonaInLocal(
                                Persona(
                                    persona.id,
                                    persona.firstname,
                                    persona.lastnamep,
                                    persona.lastnamem,
                                    persona.gender,
                                    persona.dni,
                                    persona.birthdate,
                                    persona.email,
                                    null
                                )
                            )
                            listener.onSuccess(usuario)
                            return@main
                        }
                        return@main
                    }
                    listener.onError(tokenResponse.message!!)
                } catch (e: ApiException) {
                    listener.onError(e.message!!)
                } catch (e: NoInternetException) {
                    listener.onError(e.message!!)
                } catch (e: ApiTimeOutException) {
                    listener.onError(e.message!!)
                }
            }
        } else {
            listener.onError("Complete los datos para continuar")
        }
    }

    fun onRegisterClicked(v: View) {
        listener.onRegister()
    }

    fun getLoggedInUser() = userRepo.getUsuario()

    fun onRecoveryClicked(v: View){
        listener.onRecovery()
    }
}