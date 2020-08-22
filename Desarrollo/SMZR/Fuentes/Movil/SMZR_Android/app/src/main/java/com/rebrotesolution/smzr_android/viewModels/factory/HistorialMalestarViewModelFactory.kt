package com.rebrotesolution.smzr_android.viewModels.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.rebrotesolution.smzr_android.adapters.SintomaHistorialAdapter
import com.rebrotesolution.smzr_android.viewModels.historial_malestar.HistorialMalestarViewModel

class HistorialMalestarViewModelFactory (
    private var recyclerView: RecyclerView,
    private var context: Context
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HistorialMalestarViewModel(recyclerView,context) as T
    }
}