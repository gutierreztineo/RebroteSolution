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
import com.rebrotesolution.smzr_android.api.RegisterService
import com.rebrotesolution.smzr_android.databinding.DatosPersonales3FragmentBinding
import com.rebrotesolution.smzr_android.interfaces.RegisterResultCallBacks
import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.models.saveObjectApi
import com.rebrotesolution.smzr_android.ui.extras.LoadingDialog
import com.rebrotesolution.smzr_android.viewModels.factory.DatosPersonales3ViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.register.DatosPersonales3ViewModel
import es.dmoral.toasty.Toasty
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DatosPersonales3Fragment : Fragment(), RegisterResultCallBacks {

    private lateinit var viewModel: DatosPersonales3ViewModel

    private lateinit var email: String
    private lateinit var username: String
    private lateinit var pass: String
    private lateinit var nombres: String
    private lateinit var apellidos: String
    private lateinit var dni: String
    private lateinit var genero: String
    private  var edad: Int = 0
    private lateinit var service: RegisterService
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
        genero = arguments?.getString("genero").toString()

        val activityRegisterBinding = DataBindingUtil.inflate<DatosPersonales3FragmentBinding>(inflater,R.layout.datos_personales3_fragment,container,false)
        viewModel = ViewModelProviders.of(this, DatosPersonales3ViewModelFactory(this)).get(DatosPersonales3ViewModel::class.java)

        activityRegisterBinding.datosPersonales3ViewModel = viewModel
        activityRegisterBinding.lifecycleOwner = this
        loadingDialog = LoadingDialog(requireActivity())
        return activityRegisterBinding.root
    }

    override fun valid(data: Map<String, String>) {
        loadingDialog.start()
        genero = data["genero"]!!.toString()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://abde49dc2463.ngrok.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create<RegisterService>(RegisterService::class.java)
        var usuario: Usuario? = Usuario(username,pass,null,null)

        var persona: Persona? = Persona(null,nombres,apellidos,genero,dni,edad,email,usuario)

        service.registrarPersona(persona).enqueue(object : Callback<saveObjectApi> {

            override fun onFailure(call: Call<saveObjectApi>, t: Throwable) {
                loadingDialog.dismiss()
                Toasty.error(requireContext(),t.message.toString(),Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<saveObjectApi>, response: Response<saveObjectApi>) {
                loadingDialog.dismiss()
                var resp = response?.body()
                if(resp!!.error){
                    Toasty.error(requireContext(),resp.message,Toast.LENGTH_SHORT).show()
                }else{
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }

    override fun invalid(message: String) {

    }



}
