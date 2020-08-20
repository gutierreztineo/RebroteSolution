package com.rebrotesolution.smzr_android.ui.actualizar_malestar

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rebrotesolution.smzr_android.R

class MostrarRecomendaciones : Fragment() {

    companion object {
        fun newInstance() = MostrarRecomendaciones()
    }

    private lateinit var viewModel: MostrarRecomendacionesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(MostrarRecomendacionesViewModel::class.java)
        val root = inflater.inflate(R.layout.mostrar_recomendaciones_fragment, container, false)

        return root
    }


}
