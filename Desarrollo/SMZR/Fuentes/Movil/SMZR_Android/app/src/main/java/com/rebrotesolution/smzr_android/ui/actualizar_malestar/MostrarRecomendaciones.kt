package com.rebrotesolution.smzr_android.ui.actualizar_malestar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.models.MalestarDTO
import com.rebrotesolution.smzr_android.viewModels.actualizar_malestar.MostrarRecomendacionesViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MostrarRecomendacionesViewModelFactory

class MostrarRecomendaciones : Fragment() {

    private lateinit var recyclerView: RecyclerView

    private lateinit var mostrarRecomViewModel: MostrarRecomendacionesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.mostrar_recomendaciones_fragment, container, false)
        recyclerView = root.findViewById(R.id.recicler_view_recomendaciones)
        mostrarRecomViewModel = ViewModelProviders.of(this, MostrarRecomendacionesViewModelFactory(recyclerView,requireContext(),ArrayList())).get(MostrarRecomendacionesViewModel::class.java)
        mostrarRecomViewModel.mostrarData()
        return root
    }


}
