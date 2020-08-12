package com.rebrotesolution.smzr_android.network.repository

import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.network.api.RegisterClient
import com.rebrotesolution.smzr_android.network.responses.ApiRestResponse
import com.rebrotesolution.smzr_android.network.responses.SafeApiRequest

class PersonaRepository(
    private val api: RegisterClient
) : SafeApiRequest(){

    suspend fun registrarPersona(persona: Persona): ApiRestResponse {
        return apiRequest { api.registrarPersona(persona) }
    }
}