package com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.models.TourPackage

@Dao
interface TourPackageDao {
    @Insert
    suspend fun insertFavorite(tourPackage: TourPackage)

    @Delete
    suspend fun deleteFavorite(tourPackage: TourPackage)

    @Query("SELECT * FROM tour_packages")
    suspend fun getAllFavorites(): List<TourPackage>

    @Query("SELECT * FROM tour_packages WHERE id_producto = :idProducto LIMIT 1")
    suspend fun getFavoriteById(idProducto: String): TourPackage?
}