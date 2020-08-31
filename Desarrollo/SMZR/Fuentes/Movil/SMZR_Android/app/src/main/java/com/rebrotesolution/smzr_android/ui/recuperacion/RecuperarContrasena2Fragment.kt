package com.rebrotesolution.smzr_android.ui.recuperacion

import android.content.Context
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecuperarContrasena2Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecuperarContrasena2Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var loadingBar : LoadingDialog
    private lateinit var username : String
    private lateinit var navController : NavController

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
        val rootView : View = inflater.inflate(R.layout.recuperar_contrasena2_fragment,container,false)
        val apiPassword = PasswordClient(NetworkConnectionInterceptor(requireContext()))
        val passwordRepo = PasswordRepository(apiPassword)
        val aceptarButton = rootView.findViewById<Button>(R.id.btnAceptar)
        val codigoText = rootView.findViewById<EditText>(R.id.codigo)

        username = arguments?.getString("username").toString()
        loadingBar = LoadingDialog(requireActivity())
        aceptarButton.setOnClickListener {
            val codigo = codigoText.text.toString()
            if (codigo.length == 4){
                loadingBar.start()
                Coroutines.main {
                    try {
                        val tokenResponse = passwordRepo.validateCode(codigo, username)
                        tokenResponse.token?.let {
                            loadingBar.dismiss()
                            navController.navigate(
                                R.id.go_recuperarContrasena3,
                                bundleOf("token" to it)
                            )
                            return@main
                        }
                        Toasty.error(requireContext(), "Código incorrecto", Toast.LENGTH_SHORT).show()
                        loadingBar.dismiss()
                    } catch (e: ApiException) {
                        loadingBar.dismiss()
                        Toasty.error(requireContext(), "Error desconocido", Toast.LENGTH_SHORT).show()
                    } catch (e: NoInternetException) {
                        loadingBar.dismiss()
                        Toasty.error(requireContext(), "No tiene conexión", Toast.LENGTH_SHORT).show()
                    } catch (e: ApiTimeOutException) {
                        loadingBar.dismiss()
                        Toasty.error(requireContext(), "Se superó el tiempo de carga", Toast.LENGTH_SHORT).show()
                    }
                }
            }else if(codigo.isEmpty()){
                Toasty.warning(requireContext(),"Debe llenar el código", Toast.LENGTH_SHORT).show()
            }
            else{
                Toasty.error(requireContext(), "Código inválido", Toast.LENGTH_SHORT).show()
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
         * @return A new instance of fragment RecuperarContrasena2Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecuperarContrasena2Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}