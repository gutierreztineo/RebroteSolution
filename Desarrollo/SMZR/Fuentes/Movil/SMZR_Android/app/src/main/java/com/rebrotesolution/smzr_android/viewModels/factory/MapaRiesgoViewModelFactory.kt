package com.rebrotesolution.smzr_android.viewModels.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rebrotesolution.smzr_android.interfaces.ApiResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.mapa_riesgo.MapaRiesgoViewModel

class MapaRiesgoViewModelFactory (
   private  var httplistener: ApiResultCallBacks,
   private var context: Context
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MapaRiesgoViewModel(httplistener,context) as T
    }
}