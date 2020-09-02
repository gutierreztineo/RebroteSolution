package com.rebrotesolution.smzr_android.viewModels.register

import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository
import com.rebrotesolution.smzr_android.utils.*

class DatosCuentaViewModel(
    private val listener: RegisterResultCallBacks,
    private val userRepo: UsuarioRepository,
    private val sharedPreferences: SharedPreferences
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
           usuario.username =  p0.toString()
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

    val passConfirmTextWatcher: TextWatcher
    get() = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            confirmpass = p0.toString()
            passvalid = confirmpass == usuario.password
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    fun onNextClicked(v: View){
        if(usuario.isDataComplete){
            if(usuario.isDataValid){
                if(passvalid){
                    listener.onStarted()
                    Coroutines.main {
                        try {
                            val verifica = userRepo.verificarUsernameRepetido(usuario.username,usuario.password)
                            verifica.data?.let {
                                if(it){
                                    throw UserAlreadyExistsException("El nombre de usuario ya existe, intente otro")
                                }else{
                                    var data : Map<String,String> = mapOf("username" to usuario.username, "password" to usuario.password)
                                    listener.valid(data)
                                }
                                return@main
                            }
                        } catch (e: UserAlreadyExistsException) {
                            listener?.onError(e.message!!)
                        } catch (e: NoInternetException) {
                            listener?.onError(e.message!!)
                        }catch( e: ApiTimeOutException){
                            listener.onError(e.message!!)
                        }
                    }
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
