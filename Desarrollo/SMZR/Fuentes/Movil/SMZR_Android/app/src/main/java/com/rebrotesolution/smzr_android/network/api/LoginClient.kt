package com.rebrotesolution.smzr_android.network.api

import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.models.models_api.UsuarioSendRegister
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.responses.DataBooleanResponse
import com.rebrotesolution.smzr_android.network.responses.TokenResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface LoginClient {

    @POST("api/sign_in")
    suspend fun login(@Body usuario:Usuario): Response<TokenResponse>

    @POST("api/exists")
    suspend fun validarUsernameExists(@Body usuario: Usuario): Response<DataBooleanResponse>

    companion object{
        operator  fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): LoginClient {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(2,TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://smzr.makinap.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginClient::class.java)
        }
    }
}