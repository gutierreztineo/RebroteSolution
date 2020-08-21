package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks

class MalestarForm3ViewModel(
    val listener: FormularioMalestarResultCallBacks
) : ViewModel() {

    private var resp5: Int = -1
    private var resp6: Int = -1

    fun continuar(v: View){
        if(resp5 < 0 || resp6 < 0){
            listener.incomplete("Por favor, complete las preguntas para continuar")
        }else{
            var send: Map<String, Int> = mapOf(
                "q5" to resp5,
                "q6" to resp6
            )
            listener.complete(send)
        }
    }

    fun onCustomCheckChanged5(radio: RadioGroup?, id: Int) {
        resp5 = when(id){
            R.id.id_rdbtn_q5_opc1 -> 0
            R.id.id_rdbtn_q5_opc2 -> 1
            R.id.id_rdbtn_q5_opc3 -> 2
            R.id.id_rdbtn_q5_opc4 -> 3
            else -> -1
        }
    }

    fun onCustomCheckChanged6(radio: RadioGroup?, id: Int) {
        resp6 = when(id){
            R.id.id_rdbtn_q6_opc1 -> 0
            R.id.id_rdbtn_q6_opc2 -> 1
            else -> -1
        }
    }

}
