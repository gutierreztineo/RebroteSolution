package com.rebrotesolution.smzr_android.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.network.api.LoginClient
import com.rebrotesolution.smzr_android.network.responses.AuthResponse
import com.rebrotesolution.smzr_android.network.responses.SafeApiRequest
import com.rebrotesolution.smzr_android.room.async.deleteSesionAsync
import com.rebrotesolution.smzr_android.room.db.RoomDB
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioRepository (
    private val api: LoginClient,
    private val db: RoomDB
): SafeApiRequest() {

    suspend fun userLogin(username: String, password: String): AuthResponse {
        return apiRequest { api.login(Usuario(null,username,password,null)) }
    }

    suspend fun saveSesion(usuario: Usuario) = db.getUserDao().saveSesion(usuario)

    fun getUsuario() = db.getUserDao().getUser()

    fun deleteSesion() = deleteSesionAsync(db.getUserDao(),db.getPersonaDao()).execute()
}

