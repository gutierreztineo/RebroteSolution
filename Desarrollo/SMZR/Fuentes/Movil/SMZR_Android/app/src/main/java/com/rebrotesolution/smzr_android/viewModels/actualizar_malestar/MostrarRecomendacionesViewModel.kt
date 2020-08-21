package com.rebrotesolution.smzr_android.viewModels.actualizar_malestar

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rebrotesolution.smzr_android.adapters.MostrarRecomendacionAdapter
import com.rebrotesolution.smzr_android.models.MalestarDTO

class MostrarRecomendacionesViewModel(
    var recyclerView: RecyclerView,
    var context: Context,
    var listamalestar: ArrayList<MalestarDTO>
) : ViewModel() {

    private lateinit var adapter: MostrarRecomendacionAdapter

    fun mostrarData(){
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = MostrarRecomendacionAdapter(listamalestar)
        recyclerView.adapter = adapter
    }

}
