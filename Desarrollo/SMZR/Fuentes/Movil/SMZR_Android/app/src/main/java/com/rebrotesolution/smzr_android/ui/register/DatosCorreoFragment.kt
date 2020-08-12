package com.rebrotesolution.smzr_android.ui.register

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.databinding.DatosCorreoFragmentBinding
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.viewModels.factory.DatosCorreoViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.register.DatosCorreoViewModel
import es.dmoral.toasty.Toasty


class DatosCorreoFragment : Fragment(), RegisterResultCallBacks {

    private lateinit var viewModel: DatosCorreoViewModel
    private lateinit var navController: NavController

    private lateinit var username: String
    private lateinit var pass: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        username = arguments?.getString("username").toString()
        pass = arguments?.getString("password").toString()
        val activityRegisterBinding = DataBindingUtil.inflate<DatosCorreoFragmentBinding>(inflater,R.layout.datos_correo_fragment,container,false)

        viewModel = ViewModelProviders.of(this, DatosCorreoViewModelFactory(this)).get(DatosCorreoViewModel::class.java)

        activityRegisterBinding.datosCorreoViewModel = viewModel
        activityRegisterBinding.lifecycleOwner = this

        return activityRegisterBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun valid(data: Map<String,String>) {
        var bundle = bundleOf("email" to data["email"],
                                "username" to username,
                                "password" to pass)
        navController.navigate(R.id.go_datos_personales_1,bundle);
    }

    override fun invalid(message:String) {
        Toasty.error( requireContext(),message, Toast.LENGTH_SHORT).show();
    }

    override fun onSuccess(obj: Any) {
    }

    override fun onError(message: String) {
    }

    override fun onStarted() {
    }

}
