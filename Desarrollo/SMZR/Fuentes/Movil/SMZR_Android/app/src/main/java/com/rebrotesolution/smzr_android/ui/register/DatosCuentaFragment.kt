package com.rebrotesolution.smzr_android.ui.register

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.databinding.DatosCuentaFragmentBinding
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.viewModels.factory.DatosCuentaViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.register.DatosCuentaViewModel
import es.dmoral.toasty.Toasty

class DatosCuentaFragment : Fragment(), RegisterResultCallBacks {

    private lateinit var datosCuentaViewModel: DatosCuentaViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val activityRegisterBinding = DataBindingUtil.inflate<DatosCuentaFragmentBinding>(inflater,R.layout.datos_cuenta_fragment,container,false)
        datosCuentaViewModel = ViewModelProviders.of(this, DatosCuentaViewModelFactory(this)).get(DatosCuentaViewModel::class.java)

        activityRegisterBinding.datosCuentaViewModel = datosCuentaViewModel
        activityRegisterBinding.lifecycleOwner = this

        return activityRegisterBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun valid(data: Map<String,String>) {
        var bundle = bundleOf("username" to data["username"],"password" to data["password"])
        navController.navigate(R.id.go_datos_correo);
    }

    override fun invalid(message:String) {
        Toasty.error( requireContext(),message, Toast.LENGTH_SHORT).show();
    }
}
