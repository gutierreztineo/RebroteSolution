package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks


class MalestarForm7ViewModel(
    val listener: FormularioMalestarResultCallBacks
) : ViewModel() {

    fun continuar(v: View){
        listener.complete()
    }

    fun onCustomCheckChanged(radio: RadioGroup?, id: Int) {

    }
}
