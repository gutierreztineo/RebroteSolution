package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.app.Activity
import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.ButtonAcceptHandler

class EmpezarFormularioViewModel(
    private var listener: ButtonAcceptHandler,
    private var activity: Activity
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Registre los sintomas que usted ha presentado en estos ultimos días, esta informacion es totalmente privada y solo se usara para darle una clasificacion."
    }
    private val _text_button =  MutableLiveData<String>().apply {
        value = "Registrar Malestares"
    }

    val text: LiveData<String> = _text
    val text_button: LiveData<String> = _text_button

    private val sharedPreferences = activity.getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

    fun onComenzarFormulario(v: View){
        val save = sharedPreferences.edit()
        save.putBoolean("OPEN_FORM",true)
        listener.clickOnButton()
        save.apply()
    }
}
