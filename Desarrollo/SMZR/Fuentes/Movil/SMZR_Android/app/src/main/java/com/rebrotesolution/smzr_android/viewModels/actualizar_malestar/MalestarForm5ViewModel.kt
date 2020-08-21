package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks

class MalestarForm5ViewModel(
    val listener: FormularioMalestarResultCallBacks
) : ViewModel() {

    private var resp9: Int = -1
    private var resp10: Int = -1

    fun continuar(v: View){
        if(resp9 < 0 || resp10 < 0){
            listener.incomplete("Por favor, complete las preguntas para continuar")
        }else{
            var send: Map<String, Int> = mapOf(
                "q9" to resp9,
                "q10" to resp10
            )
            listener.complete(send)
        }
    }

    fun onCustomCheckChanged9(radio: RadioGroup?, id: Int) {
        resp9 = when(id){
            R.id.id_rdbtn_q9_opc1 -> 0
            R.id.id_rdbtn_q9_opc2 -> 1
            R.id.id_rdbtn_q9_opc3 -> 1
            else -> -1
        }
    }

    fun onCustomCheckChanged10(radio: RadioGroup?, id: Int) {
        resp10 = when(id){
            R.id.id_rdbtn_q10_opc1 -> 0
            R.id.id_rdbtn_q10_opc2 -> 1
            else -> -1
        }
    }
}
