package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.view.View
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks

class MalestarForm2ViewModel(
    var listener: FormularioMalestarResultCallBacks
) : ViewModel() {

    fun continuar(v: View){
        listener.complete()
    }

}
