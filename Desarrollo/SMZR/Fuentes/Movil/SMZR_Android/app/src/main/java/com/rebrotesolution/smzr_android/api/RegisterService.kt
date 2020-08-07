package com.rebrotesolution.smzr_android.api

import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.models.saveObjectApi
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterService {

    @POST("/usuario/registrar")
    fun registrarPersona(@Body persona:Persona?): Call<saveObjectApi>
}