package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks

class MalestarForm2ViewModel(
    var listener: FormularioMalestarResultCallBacks
) : ViewModel() {

    private var resp3: Int = -1
    private var resp4: Int = -1

    fun continuar(v: View){
        if(resp3 < 0 || resp4 < 0){
            listener.incomplete("Por favor, complete las preguntas para continuar")
        }else{
            var send: Map<String, Int> = mapOf(
                "q3" to resp3,
                "q4" to resp4
            )
            listener.complete(send)
        }
    }

    fun onCustomCheckChanged3(radio: RadioGroup?, id: Int) {
        resp3 = when(id){
            R.id.id_rdbtn_q3_opc1 -> 0
            R.id.id_rdbtn_q3_opc2 -> 1
            R.id.id_rdbtn_q3_opc3 -> 2
            R.id.id_rdbtn_q3_opc4 -> 3
            else -> -1
        }
    }

    fun onCustomCheckChanged4(radio: RadioGroup?, id: Int) {
        resp4 = when(id){
            R.id.id_rdbtn_q4_opc1 -> 0
            R.id.id_rdbtn_q4_opc2 -> 1
            R.id.id_rdbtn_q4_opc3 -> 1
            R.id.id_rdbtn_q4_opc4 -> 1
            else -> -1
        }
    }

}
