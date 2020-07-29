package com.rebrotesolution.smzr_android.ui.register

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.databinding.DatosPersonales2FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.factory.DatosPersonales2ViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.register.DatosPersonales2ViewModel
import es.dmoral.toasty.Toasty
import java.util.*

class DatosPersonales2Fragment : Fragment(), RegisterResultCallBacks {

    private lateinit var viewModel: DatosPersonales2ViewModel
    private lateinit var navController: NavController
    private lateinit var datepicker: DatePicker

    private lateinit var email: String
    private lateinit var username: String
    private lateinit var pass: String
    private lateinit var nombres: String
    private lateinit var apellidos: String
    private lateinit var dni: String

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

        val activityRegisterBinding = DataBindingUtil.inflate<DatosPersonales2FragmentBinding>(inflater,R.layout.datos_personales2_fragment,container,false)

        viewModel = ViewModelProviders.of(this, DatosPersonales2ViewModelFactory(this)).get(DatosPersonales2ViewModel::class.java)
        datepicker = activityRegisterBinding.fechaNacimientoDate
        val c = Calendar.getInstance()
        datepicker.updateDate(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH))

        activityRegisterBinding.datosPersonales2ViewModel = viewModel
        activityRegisterBinding.lifecycleOwner = this

        return activityRegisterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun valid(data: Map<String, String>) {
        var bundle = bundleOf(
            "email" to email,
            "username" to username,
            "password" to pass,
            "nombres" to data[nombres],
            "apellidos" to data[apellidos],
            "dni" to data[dni],
            "edad" to data["edad"]
        )
        navController.navigate(R.id.go_datos_personales_3,bundle);
    }

    override fun invalid(message: String) {
        Toasty.error( requireContext(),message, Toast.LENGTH_SHORT).show();
    }

}
