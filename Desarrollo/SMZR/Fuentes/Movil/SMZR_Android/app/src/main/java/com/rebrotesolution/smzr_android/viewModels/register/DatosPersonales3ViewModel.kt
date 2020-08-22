package com.rebrotesolution.smzr_android.viewModels.register

import android.view.View
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.network.repository.PersonaRepository
import com.rebrotesolution.smzr_android.utils.ApiException
import com.rebrotesolution.smzr_android.utils.Coroutines
import com.rebrotesolution.smzr_android.utils.NoInternetException

class DatosPersonales3ViewModel(
    private val listener: RegisterResultCallBacks,
    private val repository: PersonaRepository,
    private val persona: Persona
) : ViewModel() {

    fun onRegisterCompleteClicked(v: View){
        listener.onStarted()
        persona.genero = "masculino"
        Coroutines.main {
            try {
                val apiResponse = repository.registrarPersona(persona)
                apiResponse.objeto?.let {
                    listener?.onSuccess(it)
                    return@main
                }
                listener?.onError(apiResponse.message!!)
            }catch (e: ApiException){
                listener?.onError(e.message!!)
            }catch (e: NoInternetException){
                listener?.onError(e.message!!)
            }
        }
    }
}
