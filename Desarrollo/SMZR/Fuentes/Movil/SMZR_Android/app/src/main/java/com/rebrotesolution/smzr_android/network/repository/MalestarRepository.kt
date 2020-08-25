package com.rebrotesolution.smzr_android.network.repository

import com.rebrotesolution.smzr_android.network.api.MalestarClient
import com.rebrotesolution.smzr_android.network.responses.ApiRestResponse
import com.rebrotesolution.smzr_android.network.responses.SafeApiRequest

class MalestarRepository(
    private var api: MalestarClient
): SafeApiRequest(){

    suspend fun getHistorialMalestares(idpersona: Int): ApiRestResponse{
        return apiRequest { api.listarMalestares(idpersona) }
    }
}