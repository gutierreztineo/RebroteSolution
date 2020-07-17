package com.rebrotesolution.smzr.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.rebrotesolution.smzr.R
import com.rebrotesolution.smzr.databinding.ActivityMainBinding
import com.rebrotesolution.smzr.interfaces.LoginResultCallBacks
import com.rebrotesolution.smzr.viewModels.factories.LoginViewModelFactory
import com.rebrotesolution.smzr.viewModels.login.LoginViewModel
import es.dmoral.toasty.Toasty

class MainActivity : AppCompatActivity() , LoginResultCallBacks{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        activityMainBinding.loginViewModel = ViewModelProviders.of(this,LoginViewModelFactory(this))
            .get(LoginViewModel::class.java)
    }

    override fun onSuccess(message: String) {
        Toasty.success(this,message,Toast.LENGTH_SHORT).show()
    }

    override fun onError(message: String) {
        Toasty.error(this,message,Toast.LENGTH_SHORT).show()
    }
}
