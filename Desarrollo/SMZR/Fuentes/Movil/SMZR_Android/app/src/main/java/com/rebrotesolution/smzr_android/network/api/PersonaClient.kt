package com.rebrotesolution.smzr_android.network.api

import com.rebrotesolution.smzr_android.models.Persona
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.responses.ApiRestResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface PersonaClient {

    @POST("test/registrar")
    suspend fun registrarPersona(@Body persona:Persona): Response<ApiRestResponse>

    companion object{
        operator  fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): PersonaClient {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://rentame-back.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PersonaClient::class.java)
        }
    }
}