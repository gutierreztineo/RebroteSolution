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
import com.rebrotesolution.smzr_android.viewModels.register.DatosCuentaViewModel

class DatosCuentaFragment : Fragment(), View.OnClickListener {

    private lateinit var datosCuentaViewModel: DatosCuentaViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        datosCuentaViewModel = ViewModelProviders.of(this).get(DatosCuentaViewModel::class.java)
       val root = inflater.inflate(R.layout.datos_cuenta_fragment, container, false)
       return root
    }

    override fun onClick(p0: View?) {
        navController.navigate(R.id.go_datos_personales_1);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>( R.id.button_action_datosp1).setOnClickListener(this)
    }
}
