package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks

class MalestarForm4ViewModel(
    val listener: FormularioMalestarResultCallBacks
) : ViewModel() {

    private var resp7: Int = -1
    private var resp8: Int = -1

    fun continuar(v: View){
        if(resp7 < 0 || resp8 < 0){
            listener.incomplete("Por favor, complete las preguntas para continuar")
        }else{
            var send: Map<String, Int> = mapOf(
                "q7" to resp7,
                "q8" to resp8
            )
            listener.complete(send)
        }
    }

    fun onCustomCheckChanged7(radio: RadioGroup?, id: Int) {
        resp7 = when(id){
            R.id.id_rdbtn_q7_opc1 -> 0
            R.id.id_rdbtn_q7_opc2 -> 1
            else -> -1
        }
    }

    fun onCustomCheckChanged8(radio: RadioGroup?, id: Int) {
        resp8 = when(id){
            R.id.id_rdbtn_q8_opc1 -> 0
            R.id.id_rdbtn_q8_opc2 -> 1
            else -> -1
        }
    }

}
