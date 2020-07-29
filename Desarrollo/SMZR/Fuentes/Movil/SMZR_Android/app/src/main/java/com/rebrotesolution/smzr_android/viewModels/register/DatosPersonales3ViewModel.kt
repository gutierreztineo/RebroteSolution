package com.rebrotesolution.smzr_android.viewModels.register

import android.view.View
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks

class DatosPersonales3ViewModel(
    private val listener: RegisterResultCallBacks
) : ViewModel() {

    fun onRegisterCompleteClicked(v: View){
        var data: Map<String,String> = mapOf("genero" to "masculino")
        listener.valid(data)
    }
}
