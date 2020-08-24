package com.rebrotesolution.smzr_android.network.repository

import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.models.models_api.Profile
import com.rebrotesolution.smzr_android.models.models_api.ProfileSendRegister
import com.rebrotesolution.smzr_android.network.api.PersonaClient
import com.rebrotesolution.smzr_android.network.responses.ApiRestResponse
import com.rebrotesolution.smzr_android.network.responses.ProfileRegisterResponse
import com.rebrotesolution.smzr_android.network.responses.SafeApiRequest
import com.rebrotesolution.smzr_android.room.db.RoomDB

class PersonaRepository(
    private val api: PersonaClient,
    private val db: RoomDB
) : SafeApiRequest(){

    suspend fun registrarPersona(persona: Persona, token: String): ProfileRegisterResponse {
        var profile = Profile(null,persona.nombres,persona.apellidop,persona.apellidom,persona.dni,persona.genero,persona.cumpleanios,persona.email)
        println("Objeto profile: " + persona.toString())
        println("TOKEN: " + token)
        return apiRequest { api.registrarPersona("Bearer " + token, ProfileSendRegister(profile)) }
    }

    fun obtenerDatosPersona() = db.getPersonaDao().getPersona()

    suspend fun savePersonaInLocal(persona: Persona) = db.getPersonaDao().savePersona(persona)


}