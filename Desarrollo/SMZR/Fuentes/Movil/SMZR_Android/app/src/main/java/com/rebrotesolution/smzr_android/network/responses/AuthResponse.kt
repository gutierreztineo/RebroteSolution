package com.rebrotesolution.smzr_android.network.responses

import com.rebrotesolution.smzr_android.models.Usuario

data class AuthResponse(
    val isSuccesfull: Boolean?,
    val message: String?,
    val usuario: Usuario?
) {

}