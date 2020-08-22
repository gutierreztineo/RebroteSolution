package com.rebrotesolution.smzr_android.viewModels.register

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.models.Persona

class DatosPersonales1ViewModel(
    private val listener: RegisterResultCallBacks
) : ViewModel() {
    private val persona: Persona

    init {
        persona = Persona(
            id_persona = 0, nombres = "", apellidos = "", genero = "",
            dni = "", edad = 0, email = "",usuario = null
        )
    }

    val nombreTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                persona.nombres = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        }

    val apellidoTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                persona.apellidos = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        }

    val dniTextWatcher: TextWatcher
        get() = object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                persona.dni = p0.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        }

    fun onNextClicked(v: View) {
        if (persona.isDatoPersonal1Valid) {
            if (persona.isDniValid) {
                var data: Map<String, String> = mapOf(
                    "nombres" to persona.nombres,
                    "apellidos" to persona.apellidos,
                    "dni" to persona.dni
                )
                listener.valid(data)
            } else {
                listener.invalid("El DNI debe ser de 8 digitos")
            }
        } else {
            listener.invalid("Complete los campos faltantes")
        }
    }

}
