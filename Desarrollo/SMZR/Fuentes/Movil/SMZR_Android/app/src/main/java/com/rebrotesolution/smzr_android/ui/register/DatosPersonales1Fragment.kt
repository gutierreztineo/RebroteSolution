package com.rebrotesolution.smzr_android.ui.register

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.viewModels.register.DatosPersonales1ViewModel

class DatosPersonales1Fragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: DatosPersonales1ViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(DatosPersonales1ViewModel::class.java)
        return inflater.inflate(R.layout.datos_personales1_fragment, container, false)
    }

    override fun onClick(p0: View?) {
        navController.navigate(R.id.go_datos_personales_2);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>( R.id.button_action_datosp2).setOnClickListener(this)
    }

}
