package com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.repository

import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.communication.ApiResponsePackages
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.db.TourPackageDao
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.models.TourPackage
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.network.RetrofitClient
import retrofit2.Response

class TourPackageRepository(private val tourPackageDao: TourPackageDao) {

    suspend fun fetchPackages(sitio: String, tipo: Int): Response<ApiResponsePackages> {
        return RetrofitClient.instance.getPackages(sitio, tipo).execute()
    }

    suspend fun addFavorite(tourPackage: TourPackage) {
        tourPackageDao.insertFavorite(tourPackage)
    }

    suspend fun removeFavorite(tourPackage: TourPackage) {
        tourPackageDao.deleteFavorite(tourPackage)
    }

    suspend fun getAllFavorites(): List<TourPackage> {
        return tourPackageDao.getAllFavorites()
    }

    suspend fun isFavorite(idProducto: String): Boolean {
        return tourPackageDao.getFavoriteById(idProducto) != null
    }
}