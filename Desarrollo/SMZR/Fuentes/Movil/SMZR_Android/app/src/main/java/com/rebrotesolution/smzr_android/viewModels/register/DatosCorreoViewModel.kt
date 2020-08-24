package com.rebrotesolution.smzr_android.viewModels.register

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.models.Persona

class DatosCorreoViewModel(
    private val listener: RegisterResultCallBacks
) : ViewModel() {
    private val persona: Persona

    init {
        persona = Persona(
            id_persona = 0, nombres = "", apellidop = "", apellidom =  "", genero = "",
            dni = "", cumpleanios =  "", email = "", usuario = null
        )
    }

    val emailTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                persona.email = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        }

    fun onNextClicked(v: View) {
        if (persona.isDatoCorreoValid) {
            var data: Map<String, String> = mapOf("email" to persona.email)
            listener.valid(data)
        } else {
            listener.invalid("El correo tiene un formato inválido")
        }
    }
}
