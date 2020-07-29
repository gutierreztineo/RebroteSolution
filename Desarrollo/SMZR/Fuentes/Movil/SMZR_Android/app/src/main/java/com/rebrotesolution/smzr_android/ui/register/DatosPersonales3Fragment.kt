package com.rebrotesolution.smzr_android.ui.register

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.rebrotesolution.smzr_android.MainActivity

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.databinding.DatosPersonales3FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.factory.DatosPersonales3ViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.register.DatosPersonales3ViewModel

class DatosPersonales3Fragment : Fragment(), RegisterResultCallBacks {

    private lateinit var viewModel: DatosPersonales3ViewModel

    private lateinit var email: String
    private lateinit var username: String
    private lateinit var pass: String
    private lateinit var nombres: String
    private lateinit var apellidos: String
    private lateinit var dni: String
    private  var edad: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        email = arguments?.getString("email").toString()
        username = arguments?.getString("username").toString()
        pass = arguments?.getString("password").toString()
        nombres = arguments?.get("nombres").toString()
        apellidos = arguments?.get("apellidos").toString()
        dni = arguments?.get("dni").toString()

        val activityRegisterBinding = DataBindingUtil.inflate<DatosPersonales3FragmentBinding>(inflater,R.layout.datos_personales3_fragment,container,false)
        viewModel = ViewModelProviders.of(this, DatosPersonales3ViewModelFactory(this)).get(DatosPersonales3ViewModel::class.java)

        activityRegisterBinding.datosPersonales3ViewModel = viewModel
        activityRegisterBinding.lifecycleOwner = this

        return activityRegisterBinding.root
    }

    override fun valid(data: Map<String, String>) {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    override fun invalid(message: String) {
    }


}
