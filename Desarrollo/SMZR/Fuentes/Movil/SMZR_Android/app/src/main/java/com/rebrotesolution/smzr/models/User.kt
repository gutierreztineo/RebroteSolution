package com.rebrotesolution.smzr.models

import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.BaseObservable

public class User(
    private var username:String,
    private var password:String

) : BaseObservable() {

    val isDataValid: Boolean
    get() = (!TextUtils.isEmpty(getUsername()) ) && getUsername().length > 5 && getPassword().length > 5

    fun getPassword() : String {
        return password
    }

    fun getUsername() : String {
        return username
    }

    fun setPassword(password : String ){
        this.password = password
    }

    fun setUsername(username : String ){
        this.username = username
    }
}