package com.rebrotesolution.smzr_android.viewModels.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.rebrotesolution.smzr_android.models.MalestarDTO
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MostrarRecomendacionesViewModel

class MostrarRecomendacionesViewModelFactory (
    private var recyclerView: RecyclerView,
    private var context: Context,
    var listarespuestas: List<Int>
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MostrarRecomendacionesViewModel(recyclerView,context,listarespuestas) as T
    }
}