package com.rebrotesolution.smzr_android.network.repository

import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.models.models_api.User
import com.rebrotesolution.smzr_android.models.models_api.UsuarioSendRegister
import com.rebrotesolution.smzr_android.network.api.LoginClient
import com.rebrotesolution.smzr_android.network.responses.DataBooleanResponse
import com.rebrotesolution.smzr_android.network.responses.DataProfileResponse
import com.rebrotesolution.smzr_android.network.responses.SafeApiRequest
import com.rebrotesolution.smzr_android.network.responses.TokenResponse
import com.rebrotesolution.smzr_android.room.async.deleteSesionAsync
import com.rebrotesolution.smzr_android.room.db.RoomDB

class UsuarioRepository (
    private val api: LoginClient,
    private val db: RoomDB
): SafeApiRequest() {

    suspend fun userLogin(username: String, password: String): TokenResponse {
        return apiRequest { api.login(Usuario(null,username,password,null)) }
    }

    suspend fun verificarUsernameRepetido(username:String, password: String): DataBooleanResponse {
        return apiRequest {  api.validarUsernameExists(Usuario(null,username,password,null) ) }
    }

    fun getUsuario() = db.getUserDao().getUser()

    fun deleteSesion() = deleteSesionAsync(db.getPersonaDao()).execute()

}

