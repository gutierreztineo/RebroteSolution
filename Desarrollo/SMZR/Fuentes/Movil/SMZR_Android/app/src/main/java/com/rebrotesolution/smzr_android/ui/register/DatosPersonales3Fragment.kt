package com.rebrotesolution.smzr_android.ui.register

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.viewModels.register.DatosPersonales3ViewModel

class DatosPersonales3Fragment : Fragment() {

    companion object {
        fun newInstance() = DatosPersonales3Fragment()
    }

    private lateinit var viewModel: DatosPersonales3ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(DatosPersonales3ViewModel::class.java)
        return inflater.inflate(R.layout.datos_personales3_fragment, container, false)
    }


}
