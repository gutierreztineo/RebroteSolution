package com.rebrotesolution.smzr_android.viewModels.cuenta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.rebrotesolution.smzr_android.interfaces.ApiResultCallBacks
import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.network.repository.PersonaRepository
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository
import com.rebrotesolution.smzr_android.utils.ApiException
import com.rebrotesolution.smzr_android.utils.Coroutines
import com.rebrotesolution.smzr_android.utils.NoInternetException
import java.util.*


class MiCuentaViewModel(
    private val personaRepo: PersonaRepository,
    private val userRepo: UsuarioRepository,
    private val listener: ApiResultCallBacks
) : ViewModel() {


    /*fun getDatosPersona(id_usuario: Int) {
        Coroutines.main {
            try {
                val apiResponse = repository.obtenerDatosPersona(id_usuario)
                apiResponse.objeto?.let {
                    var gson = Gson()
                    var json = gson.toJson(it)
                    persona = MutableLiveData<Persona>().apply {
                        value =  gson.fromJson(json,Persona::class.java)
                    }
                    apiResponse.message?.let { it -> listener.onHttpOk(it) }
                    return@main
                }
                listener?.onHttpError(apiResponse.message!!)
            }catch (e: ApiException){
                listener?.onHttpError(e.message!!)
            }catch (e: NoInternetException){
                listener?.onHttpError(e.message!!)
            }
        }
    }*/

    fun getDatosUusario() = userRepo.getUsuario()

    fun getDatosPersona() = personaRepo.obtenerDatosPersona()

    fun getEdad(fechaNac: Date): Int {
        var fechaNacimiento: Calendar = GregorianCalendar()
        fechaNacimiento.time = fechaNac
        var fechaActual: Calendar = Calendar.getInstance()
        var ano = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR)
        var mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH)
        var dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE)
        if(mes < 0 || (mes==0 && dia<0)){
            ano--
        }
        return  ano
    }

}
