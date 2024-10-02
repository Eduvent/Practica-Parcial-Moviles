package com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.network

import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.communication.ApiResponsePackages
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface TourPackageApiService {
    @GET("productossitiotipo.php")
    fun getPackages(
        @Query("sitio") sitio: String,
        @Query("tipo") tipo: Int
    ): Call<ApiResponsePackages>
}