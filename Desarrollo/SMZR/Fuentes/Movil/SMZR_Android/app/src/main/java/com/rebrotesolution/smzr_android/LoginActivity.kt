package com.rebrotesolution.smzr_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.rebrotesolution.smzr_android.databinding.ActivityLoginBinding
import com.rebrotesolution.smzr_android.interfaces.LoginResultCallBacks
import com.rebrotesolution.smzr_android.viewModels.factory.LoginViewModelFactory
import com.rebrotesolution.smzr_android.viewModels.login.LoginViewModel
import es.dmoral.toasty.Toasty

class LoginActivity : AppCompatActivity() , LoginResultCallBacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        activityMainBinding.loginViewModel = ViewModelProviders.of(this,
            LoginViewModelFactory(this)
        )
            .get(LoginViewModel::class.java)
    }

    override fun onSuccess(message: String) {
        Toasty.success(this,message, Toast.LENGTH_SHORT).show()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    override fun onError(message: String) {
        Toasty.error(this,message, Toast.LENGTH_SHORT).show()
    }

    override fun onRegister() {
        val intent = Intent(this,RegistroActivity::class.java)
        startActivity(intent)
    }
}
