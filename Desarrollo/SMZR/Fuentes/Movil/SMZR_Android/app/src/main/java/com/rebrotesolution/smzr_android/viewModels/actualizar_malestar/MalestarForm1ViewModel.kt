package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rebrotesolution.smzr_android.interfaces.FormularioMalestarResultCallBacks

class MalestarForm1ViewModel(
    private var listener: FormularioMalestarResultCallBacks
) : ViewModel() {

    private var resp1: String = ""

    fun continuar(v: View){
        listener.complete()
    }

    fun onCheckedQuest1(v: View){
        println("ASDSADSAD")
    }

}
