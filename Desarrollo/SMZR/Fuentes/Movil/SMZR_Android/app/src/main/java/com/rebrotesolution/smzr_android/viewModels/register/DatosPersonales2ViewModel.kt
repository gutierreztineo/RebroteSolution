package com.rebrotesolution.smzr_android.viewModels.register

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import java.text.SimpleDateFormat
import java.util.*

class DatosPersonales2ViewModel(
    private val listener: RegisterResultCallBacks
) : ViewModel() {

    private val now = Calendar.getInstance()
    var year : MutableLiveData<Int> = MutableLiveData(now.get(Calendar.YEAR))
    var month: MutableLiveData<Int> = MutableLiveData(now.get(Calendar.MONTH))
    var day: MutableLiveData<Int> = MutableLiveData(now.get(Calendar.DAY_OF_MONTH))
    private var edad: Int = 0
    private var cumple: String = ""

    fun onDateChanged(year: Int, month: Int, day: Int){
       var fechaNac : Calendar = GregorianCalendar(year,month,day)
        var fechaActual: Calendar = Calendar.getInstance()
        var ano = fechaActual.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR)
        var mes = fechaActual.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH)
        var dia = fechaActual.get(Calendar.DATE) - fechaNac.get(Calendar.DATE)
        if(mes < 0 || (mes==0 && dia<0)){
            ano--
        }
        edad = ano
        cumple = SimpleDateFormat("yyyy-MM-dd").format(GregorianCalendar(year,month,day).time)
    }

    fun onNextClicked(v: View){
        var data: Map<String,String> = mapOf("cumple" to cumple)
        if(edad>=18){
            listener.valid(data)
        }else{
            listener.invalid("Tienes que ser mayor de edad para poder registrarte")
        }
    }

}
