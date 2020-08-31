package com.rebrotesolution.smzr_android.ui.recuperacion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rebrotesolution.smzr_android.R
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.api.PasswordClient
import com.rebrotesolution.smzr_android.network.repository.PasswordRepository
import com.rebrotesolution.smzr_android.ui.extras.LoadingDialog
import com.rebrotesolution.smzr_android.utils.ApiException
import com.rebrotesolution.smzr_android.utils.ApiTimeOutException
import com.rebrotesolution.smzr_android.utils.Coroutines
import com.rebrotesolution.smzr_android.utils.NoInternetException
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.recuperar_contrasena1_fragment.*
import retrofit2.http.POST

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecuperarContrasena1Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecuperarContrasena1Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var loadingBar : LoadingDialog
    private lateinit var navController :  NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView :  View = inflater.inflate(R.layout.recuperar_contrasena1_fragment, container, false)
        val apiPassword = PasswordClient(NetworkConnectionInterceptor(requireContext()))
        val passwordRepo = PasswordRepository(apiPassword)
        val enviarButton = rootView.findViewById<Button>(R.id.btnEnviar)
        val nombreUsuarioText = rootView.findViewById<EditText>(R.id.usuario_text)
        loadingBar = LoadingDialog(requireActivity())
        enviarButton.setOnClickListener {
            val userName = nombreUsuarioText.text.toString()
            if (userName.length >= 3){
                loadingBar.start()
                Coroutines.main {
                    try {
                        val sendCodeResponse =  passwordRepo.sendCode(userName)
                        sendCodeResponse.message?.let {
                            if (it.equals("Usuario no encontrado")){
                                loadingBar.dismiss()
                                Toasty.error(requireContext(),"Usuario no encontrado", Toast.LENGTH_SHORT).show()
                                return@main
                            }
                            else{
                                loadingBar.dismiss()
                                navController.navigate(R.id.go_recuperarContrasena2, bundleOf("username" to userName))
                                return@main
                            }
                        }
                        loadingBar.dismiss()
                    }catch (e: ApiException){
                        loadingBar.dismiss()
                        Toasty.error(requireContext(), "Error desconocido", Toast.LENGTH_SHORT).show()
                    }catch (e: NoInternetException){
                        loadingBar.dismiss()
                        Toasty.error(requireContext(), "No tiene conexión", Toast.LENGTH_SHORT).show()
                    }catch( e: ApiTimeOutException){
                        loadingBar.dismiss()
                        Toasty.error(requireContext(), "Se superó el tiempo de carga", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            else if(userName.isEmpty()){
                Toasty.warning(requireContext(),"Debe llenar el nombre de usuario", Toast.LENGTH_SHORT).show()
            }
            else{
                Toasty.error(requireContext(), "Ingrese un usuario válido", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecuperarContrasena1Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecuperarContrasena1Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}