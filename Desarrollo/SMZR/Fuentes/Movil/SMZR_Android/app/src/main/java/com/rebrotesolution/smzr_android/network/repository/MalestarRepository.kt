package com.rebrotesolution.smzr_android.network.repository

import com.rebrotesolution.smzr_android.models.models_api.ProfileAilmentSendRegister
import com.rebrotesolution.smzr_android.network.api.MalestarClient
import com.rebrotesolution.smzr_android.network.responses.*

class MalestarRepository(
    private var api: MalestarClient
) : SafeApiRequest() {


    suspend fun obtenerHistorialMalestar(token: String): ListDataProfileAilmentResponse {
        var lista_niveles = apiRequest { api.getNivelesMalestar("Bearer $token") }

        var historial = apiRequest { api.getHistorialMalestar("Bearer $token") }
        historial.data.forEach { historial ->
            historial.descripcion =
                obtenerNivelMalestarPorId(lista_niveles, historial.ailment_levels_id)
        }
        return historial
    }

    suspend fun registrarMalestarPersona(
        token: String,
        malestar: ProfileAilmentSendRegister
    ): DataProfileAilmentResponse {
        return apiRequest { api.registrarMalestar("Bearer $token", malestar) }
    }

    private fun obtenerNivelMalestarPorId(lista: ListDataAilmentLevelResponse, id: Int): String {
        return (lista.data.find { malestar -> malestar.id == id })?.description!!
    }


}