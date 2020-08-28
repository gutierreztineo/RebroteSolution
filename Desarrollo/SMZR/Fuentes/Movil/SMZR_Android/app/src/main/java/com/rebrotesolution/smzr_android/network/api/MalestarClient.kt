package com.rebrotesolution.smzr_android.network.api

import com.rebrotesolution.smzr_android.models.models_api.ProfileAilmentSendRegister
import com.rebrotesolution.smzr_android.network.NetworkConnectionInterceptor
import com.rebrotesolution.smzr_android.network.responses.ApiRestResponse
import com.rebrotesolution.smzr_android.network.responses.DataProfileAilmentResponse
import com.rebrotesolution.smzr_android.network.responses.ListDataAilmentLevelResponse
import com.rebrotesolution.smzr_android.network.responses.ListDataProfileAilmentResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface MalestarClient {

    @GET("api/ailment_levels")
    suspend fun getNivelesMalestar(@Header("Authorization") token: String): Response<ListDataAilmentLevelResponse>

    @GET("api/profile_ailments")
    suspend fun getHistorialMalestar(@Header("Authorization") token: String): Response<ListDataProfileAilmentResponse>

    @POST("api/profile_ailments")
    suspend fun registrarMalestar(@Header("Authorization") token: String, @Body profileAilment: ProfileAilmentSendRegister) : Response<DataProfileAilmentResponse>

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
                .baseUrl("https://smzr.makinap.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MalestarClient::class.java)
        }
    }
}