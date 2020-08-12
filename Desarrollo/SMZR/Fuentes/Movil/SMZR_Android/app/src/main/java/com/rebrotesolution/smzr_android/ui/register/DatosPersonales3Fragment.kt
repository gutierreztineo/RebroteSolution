package com.rebrotesolution.smzr_android.ui.register

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.rebrotesolution.smzr_android.MainActivity

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.databinding.DatosPersonales3FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.ui.extras.LoadingDialog
import com.rebrotesolution.smzr_android.network.api.RegisterClient
import com.rebrotesolution.smzr_android.network.repository.PersonaRepository
import com.rebrotesolution.smzr_android.viewModels.factory.DatosPersonales3ViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.register.DatosPersonales3ViewModel
import es.dmoral.toasty.Toasty

class DatosPersonales3Fragment : Fragment(), RegisterResultCallBacks {

    private lateinit var viewModel: DatosPersonales3ViewModel

    private lateinit var email: String
    private lateinit var username: String
    private lateinit var pass: String
    private lateinit var nombres: String
    private lateinit var apellidos: String
    private lateinit var dni: String
    private  var edad: Int = 0
    private lateinit var loadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        email = arguments?.getString("email").toString()
        username = arguments?.getString("username").toString()
        pass = arguments?.getString("password").toString()
        nombres = arguments?.getString("nombres").toString()
        apellidos = arguments?.getString("apellidos").toString()
        dni = arguments?.getString("dni").toString()
        edad = arguments?.getString("edad")!!.toInt()

        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        val api = RegisterClient(networkConnectionInterceptor)
        val repository = PersonaRepository(api)
        var usu = Usuario(id_usuario = null,username = username,password = pass,token = null)
        var persona = Persona(id_persona = null,usuario = usu,apellidos = apellidos,nombres = nombres,email = email,dni = dni, edad = edad,genero = "")

        val activityRegisterBinding = DataBindingUtil.inflate<DatosPersonales3FragmentBinding>(inflater,R.layout.datos_personales3_fragment,container,false)
        viewModel = ViewModelProviders.of(this, DatosPersonales3ViewModelFactory(this,repository = repository,persona = persona)).get(DatosPersonales3ViewModel::class.java)

        activityRegisterBinding.datosPersonales3ViewModel = viewModel
        activityRegisterBinding.lifecycleOwner = this
        loadingDialog = LoadingDialog(requireActivity())
        return activityRegisterBinding.root
    }

    override fun valid(data: Map<String, String>) {
    }

    override fun invalid(message: String) {
    }

    override fun onSuccess(obj: Any) {
        loadingDialog.dismiss()
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    override fun onError(message: String) {
        loadingDialog.dismiss()
        Toasty.warning(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

    override fun onStarted() {
        loadingDialog.start()
    }


}
