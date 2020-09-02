package com.rebrotesolution.smzr_android.ui.historial_malestar

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.models.*
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.api.MalestarClient
import com.rebrotesolution.smzr_android.network.repository.MalestarRepository
import com.rebrotesolution.smzr_android.viewModels.factory.HistorialMalestarViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.historial_malestar.HistorialMalestarViewModel
import kotlin.collections.ArrayList

class HistorialMalestar : Fragment() {

    private lateinit var historialViewModel: HistorialMalestarViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        val api = MalestarClient(networkConnectionInterceptor)
        val repo = MalestarRepository(api)
        val sharedPreferences = requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val root = inflater.inflate(R.layout.historial_fragment,container,false)
        recyclerView = root.findViewById(R.id.recicler_view_historial)
        historialViewModel = ViewModelProviders.of(this,HistorialMalestarViewModelFactory(recyclerView,requireContext(),repo,sharedPreferences)).get(HistorialMalestarViewModel::class.java)
        historialViewModel.cargarLista().observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()){
                var text: TextView = root.findViewById(R.id.label_sin_historial)
                text.isVisible = true
                recyclerView.isVisible = false
            }else{
                var text: TextView = root.findViewById(R.id.label_sin_historial)
                text.isVisible = false
                recyclerView.isVisible = true
                historialViewModel.mostrarData()
            }
        })

        return root
    }

}
