package com.rebrotesolution.smzr_android.viewModels.factory

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.rebrotesolution.smzr_android.interfaces.ApiResultCallBacks
import com.rebrotesolution.smzr_android.interfaces.ButtonAcceptHandler
import com.rebrotesolution.smzr_android.interfaces.StartCallBacks
import com.rebrotesolution.smzr_android.models.MalestarDTO
import com.rebrotesolution.smzr_android.network.repository.MalestarRepository
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MostrarRecomendacionesViewModel

class MostrarRecomendacionesViewModelFactory (
    private var recyclerView: RecyclerView,
    private var context: Context,
    private var listarespuestas: List<Int>,
    private val loading: StartCallBacks,
    private val apiResults: ApiResultCallBacks,
    private val repository: MalestarRepository,
    private val sharedPreferences: SharedPreferences

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MostrarRecomendacionesViewModel(recyclerView,context,listarespuestas,loading,apiResults,repository,sharedPreferences) as T
    }
}