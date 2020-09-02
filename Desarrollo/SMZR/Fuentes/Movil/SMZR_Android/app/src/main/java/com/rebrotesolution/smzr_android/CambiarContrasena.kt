package com.rebrotesolution.smzr_android

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.api.PasswordClient
import com.rebrotesolution.smzr_android.network.repository.PasswordRepository
import com.rebrotesolution.smzr_android.ui.extras.LoadingDialog
import com.rebrotesolution.smzr_android.utils.ApiException
import com.rebrotesolution.smzr_android.utils.ApiTimeOutException
import com.rebrotesolution.smzr_android.utils.Coroutines
import com.rebrotesolution.smzr_android.utils.NoInternetException
import es.dmoral.toasty.Toasty

class CambiarContrasena : AppCompatActivity(), View.OnClickListener {
    lateinit var passwordRepo : PasswordRepository
    lateinit var passwordText : EditText
    lateinit var repeatPassText : EditText
    lateinit var confirmarButton : Button
    lateinit var loadingBar : LoadingDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cambiar_contrasena)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        confirmarButton = findViewById<Button>(R.id.btnConfirmar)
        val apiPassword = PasswordClient(NetworkConnectionInterceptor(this))
        passwordRepo = PasswordRepository(apiPassword)
        passwordText = findViewById<EditText>(R.id.text_nueva_contrasena)
        repeatPassText = findViewById<EditText>(R.id.text_repetir_contrasena)
        loadingBar  = LoadingDialog(this)
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        toolbar.title="SMZR"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        confirmarButton.setOnClickListener(this)

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(p0: View?) {
        val pass1 = passwordText.text.toString()
        val pass2 = repeatPassText.text.toString()
        if((pass1 == pass2) && pass1.length>4 && pass2.length>4){
            loadingBar.start()
            Coroutines.main {
                try {
                    val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
                    var token = sharedPreferences.getString("TOKEN", "").toString()
                    val sendCodeResponse =  passwordRepo.changePassword(token,pass1)
                    sendCodeResponse.message?.let {
                        if (it.equals("Se cambio la contraseña correctamente")){
                            Toasty.success(this,"Se cambió la contraseña correctamente", Toast.LENGTH_SHORT).show()
                            loadingBar.dismiss()
                            finish()
                            return@main
                        }
                        else{
                            loadingBar.dismiss()
                            Toasty.error(this, "Error desconocido", Toast.LENGTH_SHORT).show()
                            return@main
                        }
                    }
                    loadingBar.dismiss()
                }catch (e: ApiException){
                    loadingBar.dismiss()
                    Toasty.error(this, "Error desconocido", Toast.LENGTH_SHORT).show()
                }catch (e: NoInternetException){
                    loadingBar.dismiss()
                    Toasty.error(this, "No tiene conexión", Toast.LENGTH_SHORT).show()
                }catch( e: ApiTimeOutException){
                    loadingBar.dismiss()
                    Toasty.error(this, "Se superó el tiempo de carga", Toast.LENGTH_SHORT).show()
                }
            }

        }
        else
            if(passwordText.text.toString().isEmpty()||repeatPassText.text.toString().isEmpty()){
                Toasty.warning(this,"Debe llenar todos los campos", Toast.LENGTH_SHORT).show()
            }
            else
                if(pass1.length <5 || pass2.length<5){
                    Toasty.warning(this, "La contraseña debe tener mínimo 5 caracteres", Toast.LENGTH_SHORT).show()
                }
                else
                    if(pass2 != pass1){
                        Toasty.error(this, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
                    }
    }
}
