package com.rebrotesolution.smzr_android.network.repository

import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.network.api.PersonaClient
import com.rebrotesolution.smzr_android.network.responses.ApiRestResponse
import com.rebrotesolution.smzr_android.network.responses.SafeApiRequest
import com.rebrotesolution.smzr_android.room.db.RoomDB

class PersonaRepository(
    private val api: PersonaClient,
    private val db: RoomDB
) : SafeApiRequest(){

    suspend fun registrarPersona(persona: Persona): ApiRestResponse {
        return apiRequest { api.registrarPersona(persona) }
    }

    fun obtenerDatosPersona() = db.getPersonaDao().getPersona()

    suspend fun savePersonaInLocal(persona: Persona) = db.getPersonaDao().savePersona(persona)


}