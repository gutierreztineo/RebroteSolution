package com.rebrotesolution.smzr_android.network.repository

import com.rebrotesolution.smzr_android.CambiarContrasena
import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.models.models_api.*
import com.rebrotesolution.smzr_android.network.api.LoginClient
import com.rebrotesolution.smzr_android.network.api.PasswordClient
import com.rebrotesolution.smzr_android.network.responses.SafeApiRequest
import com.rebrotesolution.smzr_android.network.responses.SendCodeResponse
import com.rebrotesolution.smzr_android.network.responses.TokenResponse
import com.rebrotesolution.smzr_android.room.async.deleteSesionAsync
import com.rebrotesolution.smzr_android.room.db.RoomDB

class PasswordRepository (
    private val api: PasswordClient
    ): SafeApiRequest() {

        suspend fun sendCode(username: String) : SendCodeResponse{
            return apiRequest { api.enviarCodigo( UserSendCode(username))}
        }

        suspend fun validateCode(codigo : String, username: String) : TokenResponse{
            return apiRequest { api.validarCodigo(ValidateCode(codigo,username)) }
        }

        suspend fun changePassword(token : String, password: String) : SendCodeResponse{
            return apiRequest { api.cambiarContrasena("Bearer "+token, ChangePassword(password))}
        }
    }

