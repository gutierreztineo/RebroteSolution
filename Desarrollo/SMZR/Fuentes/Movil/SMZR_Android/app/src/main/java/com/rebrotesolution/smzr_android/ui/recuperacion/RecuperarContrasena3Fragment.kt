package com.rebrotesolution.smzr_android.ui.recuperacion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.NavController
import com.rebrotesolution.smzr_android.MainActivity
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
 * Use the [RecuperarContrasena3Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecuperarContrasena3Fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var loadingBar : LoadingDialog
    private lateinit var username : String
    private lateinit var token :String

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
        val rootView : View = inflater.inflate(R.layout.recuperar_contrasena3_fragment,container,false)
        val apiPassword = PasswordClient(NetworkConnectionInterceptor(requireContext()))
        val passwordRepo = PasswordRepository(apiPassword)
        val guardarCambiosButton = rootView.findViewById<Button>(R.id.btnGuardarCambios)
        val passwordText = rootView.findViewById<EditText>(R.id.text_nueva_contrasena)
        val repeatPassText = rootView.findViewById<EditText>(R.id.text_repetir_contrasena)
        lateinit var password : String
        token = arguments?.getString("token").toString()
        loadingBar = LoadingDialog(requireActivity())
        guardarCambiosButton.setOnClickListener {
            if((passwordText.text.toString() == repeatPassText.text.toString()) &&
                passwordText.text.toString().length>3 && repeatPassText.text.toString().length>3){
               var password = passwordText.text.toString()
                loadingBar.start()
                Coroutines.main {
                    try {
                        val sendCodeResponse =  passwordRepo.changePassword(token,password)
                        sendCodeResponse.message?.let {
                            if (it.equals("Se cambio la contraseña correctamente")){
                                Toasty.success(requireContext(),"Se cambió la contraseña correctamente", Toast.LENGTH_SHORT).show()
                                val sharedPreferences = requireContext().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
                                val editor = sharedPreferences.edit()
                                editor.putString("TOKEN",token)
                                editor.apply()
                                loadingBar.dismiss()
                                val intent = Intent(requireActivity(), MainActivity::class.java)
                                startActivity(intent)
                                requireActivity().finish()
                                return@main
                            }
                            else{
                                loadingBar.dismiss()
                                Toasty.error(requireContext(), "Error desconocido", Toast.LENGTH_SHORT).show()
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

            }else if(passwordText.text.toString().isEmpty()||repeatPassText.text.toString().isEmpty()){
                Toasty.warning(requireContext(),"Debe llenar los campos", Toast.LENGTH_SHORT).show()
            }
            else{
                Toasty.error(requireContext(), "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
            }
        }

        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecuperarContrasena3Fragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecuperarContrasena3Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}