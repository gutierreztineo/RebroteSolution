package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.ButtonAcceptHandler

class EmpezarFormularioViewModel(
    private var listener: ButtonAcceptHandler
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Registre los sintomas que usted ha presentado en estos ultimos días, esta informacion es totalmente privada y solo se usara para darle una clasificacion."
    }
    private val _text_button =  MutableLiveData<String>().apply {
        value = "Registrar Malestares"
    }
    val text: LiveData<String> = _text
    val text_button: LiveData<String> = _text_button

    fun onComenzarFormulario(v: View){
        listener.clickOnButton()
    }
}
