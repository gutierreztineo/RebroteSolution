package com.rebrotesolution.smzr_android.viewModels.factory

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.rebrotesolution.smzr_android.adapters.SintomaHistorialAdapter
import com.rebrotesolution.smzr_android.network.repository.MalestarRepository
import com.rebrotesolution.smzr_android.viewModels.historial_malestar.HistorialMalestarViewModel

class HistorialMalestarViewModelFactory (
    private var recyclerView: RecyclerView,
    private var context: Context,
    private var repository: MalestarRepository,
    private var sharedPreferences: SharedPreferences
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HistorialMalestarViewModel(recyclerView,context,repository,sharedPreferences) as T
    }
}