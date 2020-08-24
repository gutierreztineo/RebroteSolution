package com.rebrotesolution.smzr_android.ui.cuenta

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.rebrotesolution.smzr_android.CambiarContrasena

import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.interfaces.ApiResultCallBacks
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.api.LoginClient
import com.rebrotesolution.smzr_android.network.api.PersonaClient
import com.rebrotesolution.smzr_android.network.repository.PersonaRepository
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository
import com.rebrotesolution.smzr_android.room.db.RoomDB
import com.rebrotesolution.smzr_android.viewModels.cuenta.MiCuentaViewModel
import com.rebrotesolution.smzr_android.viewModels.factory.MiCuentaViewModelFactory
import es.dmoral.toasty.Toasty
import java.text.SimpleDateFormat
import java.util.*

class MiCuenta : Fragment(), ApiResultCallBacks {

    private lateinit var miCuentaViewModel: MiCuentaViewModel
    private lateinit var textNamePerson: TextView
    private lateinit var textDniPerson: TextView
    private lateinit var textEdadPerson: TextView
    private lateinit var textemail: TextView
    private lateinit var textgenero: TextView
    private lateinit var buttonChangePassword: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val networkConnectionInterceptor = NetworkConnectionInterceptor(requireContext())
        val apilogin = LoginClient(networkConnectionInterceptor)
        val apipersona = PersonaClient(networkConnectionInterceptor)
        val db = RoomDB(requireContext())
        val repositoryUsuario = UsuarioRepository(apilogin, db)
        val repositoryPersona = PersonaRepository(apipersona, db)

        miCuentaViewModel = ViewModelProviders.of(
            this,
            MiCuentaViewModelFactory(
                repositoryPersona = repositoryPersona,
                repositoryUser = repositoryUsuario,
                listener = this
            )
        ).get(MiCuentaViewModel::class.java)
        val root = inflater.inflate(R.layout.mi_cuenta_fragment, container, false)
        textNamePerson = root.findViewById(R.id.text_name_person)
        textDniPerson = root.findViewById(R.id.text_dni_person)
        textEdadPerson = root.findViewById(R.id.text_edad_person)
        textemail = root.findViewById(R.id.text_email_person)
        textgenero = root.findViewById(R.id.text_genero_person)
        buttonChangePassword = root.findViewById(R.id.btn_cambiar_password)

        miCuentaViewModel.getDatosPersona().observe(viewLifecycleOwner, Observer { persona ->
            if (persona != null) {
                var fecha: Date = SimpleDateFormat("yyyy-MM-dd").parse(persona.cumpleanios)

                textNamePerson.text =
                    persona.nombres + ", " + persona.apellidop + " " + persona.apellidom
                textDniPerson.text = "DNI: " + persona.dni
                textEdadPerson.text = "" + miCuentaViewModel.getEdad(fecha) + " años"
                textemail.text = persona.email
                textgenero.text = persona.genero
            }
        })


        buttonChangePassword.setOnClickListener { view ->
            val intent = Intent(activity, CambiarContrasena::class.java)
            startActivity(intent)

        }
        return root
    }


    override fun onHttpOk(message: String) {
        Toasty.success(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onHttpError(message: String) {
        Toasty.error(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}
