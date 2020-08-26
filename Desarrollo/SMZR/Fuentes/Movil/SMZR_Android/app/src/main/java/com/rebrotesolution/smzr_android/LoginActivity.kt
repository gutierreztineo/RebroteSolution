package com.rebrotesolution.smzr_android

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.room.RoomDatabase
import com.rebrotesolution.smzr_android.databinding.ActivityLoginBinding
import com.rebrotesolution.smzr_android.interfaces.LoginResultCallBacks
import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.api.LoginClient
import com.rebrotesolution.smzr_android.network.api.PersonaClient
import com.rebrotesolution.smzr_android.network.repository.PersonaRepository
import com.rebrotesolution.smzr_android.network.repository.UsuarioRepository
import com.rebrotesolution.smzr_android.room.db.RoomDB
import com.rebrotesolution.smzr_android.ui.extras.LoadingDialog
import com.rebrotesolution.smzr_android.viewModels.factory.LoginViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.login.LoginViewModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , LoginResultCallBacks {

    private lateinit var loadingDialog: LoadingDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
        val apiuser = LoginClient(networkConnectionInterceptor)
        val db = RoomDB(this)
        val userRepo = UsuarioRepository(apiuser, db)
        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        val viewModel = ViewModelProviders.of(this,LoginViewModelFactory(this, userRepo,sharedPreferences)).get(LoginViewModel::class.java)
        val activityMainBinding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        activityMainBinding.loginViewModel = viewModel

        loadingDialog = LoadingDialog(this)

        var token = sharedPreferences.getString("TOKEN",null)
        if(token!=null){
            Intent(this,MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }/*
        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if(user!=null){
                Intent(this,MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })*/
    }

    override fun onStarted() {
        loadingDialog.start()

    }

    override fun onSuccess(usuario: Usuario) {
        loadingDialog.dismiss()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onError(message: String) {
        loadingDialog.dismiss()
        Toasty.error(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun onRegister() {
        Intent(this,RegistroActivity::class.java).also{
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }
    override fun onRecovery(){
        val intent = Intent(this,RecuperarContrasenaActivity::class.java)
        startActivity(intent)
    }
}
