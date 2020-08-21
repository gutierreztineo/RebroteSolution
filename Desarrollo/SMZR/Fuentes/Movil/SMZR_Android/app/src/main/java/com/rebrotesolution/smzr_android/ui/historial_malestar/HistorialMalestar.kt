package com.rebrotesolution.smzr_android.ui.historial_malestar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.adapters.SintomaHistorialAdapter
import com.rebrotesolution.smzr_android.models.*
import com.rebrotesolution.smzr_android.viewModels.factory.HistorialMalestarViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.historial_malestar.HistorialMalestarViewModel
import java.util.*
import kotlin.collections.ArrayList

class HistorialMalestar : Fragment() {

    private lateinit var historialViewModel: HistorialMalestarViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.historial_fragment,container,false)
        recyclerView = root.findViewById(R.id.recicler_view_historial)
        historialViewModel = ViewModelProviders.of(this,HistorialMalestarViewModelFactory(recyclerView,requireContext())).get(HistorialMalestarViewModel::class.java)
        historialViewModel.cargarLista()
        historialViewModel.mostrarData()
        return root
    }

}
