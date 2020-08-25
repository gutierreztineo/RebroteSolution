package com.rebrotesolution.smzr_android.network.api

import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.responses.ApiRestResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface MalestarClient {

    @POST("test/listarMalestar/{idpersona}")
    suspend fun listarMalestares(@Path("idpersona") idpersona: Int): Response<ApiRestResponse>

    companion object{
        operator  fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MalestarClient {
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
                .create(MalestarClient::class.java)
        }
    }
}