package com.rebrotesolution.smzr_android.network.api

import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.models.models_api.ChangePassword
import com.rebrotesolution.smzr_android.models.models_api.UserSendCode
import com.rebrotesolution.smzr_android.models.models_api.UsuarioSendRegister
import com.rebrotesolution.smzr_android.models.models_api.ValidateCode
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.responses.SendCodeResponse
import com.rebrotesolution.smzr_android.network.responses.TokenResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface PasswordClient {

    @POST("api/send_code")
    suspend fun enviarCodigo(@Body userSendCode: UserSendCode): Response<SendCodeResponse>

    @POST("api/validate_code")
    suspend fun validarCodigo(@Body validateCode: ValidateCode ): Response<TokenResponse>

    @POST("api/change_pass")
    suspend fun cambiarContrasena(@Header("Authorization") token : String, @Body changePassword: ChangePassword): Response<SendCodeResponse>

    companion object{
        operator  fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): PasswordClient {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://smzr.makinap.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PasswordClient::class.java)
        }
    }

}