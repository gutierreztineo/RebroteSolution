package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks

class MalestarForm6ViewModel(
    val listener: FormularioMalestarResultCallBacks
) : ViewModel() {

    private var resp11: Int = -1
    private var resp12: Int = -1

    fun continuar(v: View){
        if(resp11 < 0 || resp12 < 0){
            listener.incomplete("Por favor, complete las preguntas para continuar")
        }else{
            var send: Map<String, Int> = mapOf(
                "q11" to resp11,
                "q12" to resp12
            )
            listener.complete(send)
        }
    }

    fun onCustomCheckChanged11(radio: RadioGroup?, id: Int) {
        resp11 = when(id){
            R.id.id_rdbtn_q11_opc1 -> 0
            R.id.id_rdbtn_q11_opc2 -> 1
            else -> -1
        }
    }

    fun onCustomCheckChanged12(radio: RadioGroup?, id: Int) {
        resp12 = when(id){
            R.id.id_rdbtn_q12_opc1 -> 0
            R.id.id_rdbtn_q12_opc2 -> 1
            else -> -1
        }
    }
}
