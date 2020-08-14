package com.rebrotesolution.smzr_android.network.responses

import com.rebrotesolution.smzr_android.models.Persona

data class AuthResponse(
    val isSuccesfull: Boolean?,
    val message: String?,
    val persona: Persona?
) {

}