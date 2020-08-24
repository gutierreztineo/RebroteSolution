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
import com.rebrotesolution.smzr_android.databinding.DatosPersonales1FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.factory.DatosPersonales1ViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.register.DatosPersonales1ViewModel
import es.dmoral.toasty.Toasty

class DatosPersonales1Fragment : Fragment(), RegisterResultCallBacks {

    private lateinit var viewModel: DatosPersonales1ViewModel
    private lateinit var navController: NavController

    private lateinit var email: String
    private var username: String = ""
    private var password: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        username = arguments?.getString("username").toString()
        password = arguments?.getString("password").toString()
        email = arguments?.getString("email").toString()

        val activityRegisterBinding = DataBindingUtil.inflate<DatosPersonales1FragmentBinding>(inflater,R.layout.datos_personales1_fragment,container,false)

        viewModel = ViewModelProviders.of(this, DatosPersonales1ViewModelFactory(this)).get(DatosPersonales1ViewModel::class.java)

        activityRegisterBinding.datosPersonales1ViewModel = viewModel
        activityRegisterBinding.lifecycleOwner = this

        return activityRegisterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun valid(data: Map<String, String>) {
        var bundle = bundleOf(
            "username" to username,
            "password" to password,
            "email" to email,
            "nombres" to data["nombres"],
            "apellidop" to data["apellidop"],
            "apellidom" to data["apellidom"],
            "dni" to data["dni"]
        )
        navController.navigate(R.id.go_datos_personales_2,bundle);
    }

    override fun invalid(message: String) {
        Toasty.error( requireContext(),message, Toast.LENGTH_SHORT).show();
    }

    override fun onSuccess(obj: Any) {
    }

    override fun onError(message: String) {
    }

    override fun onStarted() {
    }

}
