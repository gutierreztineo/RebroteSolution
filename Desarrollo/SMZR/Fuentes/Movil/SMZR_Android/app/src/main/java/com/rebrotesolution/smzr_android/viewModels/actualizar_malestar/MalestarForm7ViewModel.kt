package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks


class MalestarForm7ViewModel(
    val listener: FormularioMalestarResultCallBacks
) : ViewModel() {
    var resp13: Int = -1
    var resp14: Int = -1

    fun continuar(v: View){
        if(resp13<0 || resp14<0){
            listener.incomplete("Por favor, complete las preguntas para continuar")
        }else{
            var send: Map<String, Int> = mapOf(
                "q13" to resp13,
                "q14" to resp14
            )
            listener.complete(send)
        }
    }

    fun onCustomCheckChanged13(radio: RadioGroup?, id: Int) {
        resp13 = when(id){
            R.id.id_rdbtn_q13_opc1 -> 0
            R.id.id_rdbtn_q13_opc2 -> 1
            R.id.id_rdbtn_q13_opc3 -> 2
            R.id.id_rdbtn_q13_opc4 -> 4
            else -> -1
        }
    }

    fun onCustomCheckChanged14(radio: RadioGroup?, id: Int) {
        resp14 = when(id){
            R.id.id_rdbtn_q14_opc1 -> 0
            R.id.id_rdbtn_q14_opc2 -> 1
            else -> -1
        }
    }
}
