package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.view.View
import android.widget.RadioGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks

class MalestarForm1ViewModel(
    private var listener: FormularioMalestarResultCallBacks
) : ViewModel() {

    private var resp1: Int = -1
    private var resp2: Int = -1

    fun continuar(v: View){
        if(resp1 < 0 || resp2 < 0){
            listener.incomplete("Por favor, complete las preguntas para continuar")
        }else{
            var send: Map<String, Int> = mapOf(
                "q1" to resp1,
                "q2" to resp2
            )
            listener.complete(send)
        }
    }

    fun onCustomCheckChanged1(radio: RadioGroup?, id: Int) {
        resp1 = when(id){
            R.id.id_rdbtn_q1_opc1 -> 0
            R.id.id_rdbtn_q1_opc2 -> 1
            else -> -1
        }
    }

    fun onCustomCheckChanged2(radio: RadioGroup?, id: Int) {
        resp2 = when(id){
            R.id.id_rdbtn_q2_opc1 -> 0
            R.id.id_rdbtn_q2_opc2 -> 1
            R.id.id_rdbtn_q2_opc3 -> 1
            R.id.id_rdbtn_q2_opc4 -> 1
            else -> -1
        }
    }

}
