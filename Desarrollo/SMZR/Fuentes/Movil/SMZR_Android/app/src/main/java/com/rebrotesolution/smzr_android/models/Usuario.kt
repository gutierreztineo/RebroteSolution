package com.rebrotesolution.smzr_android.models

import android.text.TextUtils
import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "usuario"
)
data class Usuario(

    @PrimaryKey
    @ColumnInfo(name="id_usuario")
    var id_usuario: Int?,
    
    @ColumnInfo(name="username")
    var username:String,

    @ColumnInfo(name="password")
    var password:String,

    @ColumnInfo(name="token")
    var token: String?

) : BaseObservable() {

    val isDataValid: Boolean
    get() =  password.length >= 5

    val isDataComplete: Boolean
    get() = (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) )



}