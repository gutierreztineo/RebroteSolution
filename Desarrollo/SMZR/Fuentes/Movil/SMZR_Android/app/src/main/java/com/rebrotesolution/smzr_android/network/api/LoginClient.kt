package com.rebrotesolution.smzr_android.network.api

import com.rebrotesolution.smzr_android.models.Usuario
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.responses.AuthResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginClient {

    @POST("test/login")
    suspend fun login(@Body usuario:Usuario): Response<AuthResponse>

    companion object{
        operator  fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): LoginClient {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://rentame-back.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginClient::class.java)
        }
    }
}