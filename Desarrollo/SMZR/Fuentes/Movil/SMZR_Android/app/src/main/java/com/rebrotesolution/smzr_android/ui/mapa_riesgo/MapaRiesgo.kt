package com.rebrotesolution.smzr_android.ui.mapa_riesgo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.viewModels.mapa_riesgo.MapaRiesgoViewModel

class MapaRiesgo : Fragment() {


    private lateinit var mapaRiesgoViewModel: MapaRiesgoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mapaRiesgoViewModel = ViewModelProviders.of(this).get(MapaRiesgoViewModel::class.java)
        val root = inflater.inflate(R.layout.mapa_riesgo_fragment, container, false)
        val textView : TextView = root.findViewById(R.id.text_home)
        mapaRiesgoViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }


}
