package com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dev.formandocodigo.com/ServicioTour/"

    val instance: TourPackageApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(TourPackageApiService::class.java)
    }
}