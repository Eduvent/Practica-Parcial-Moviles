package com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_packages")
data class TourPackage(
    @PrimaryKey
    @ColumnInfo(name = "id_producto")
    val idProducto: String,

    @ColumnInfo(name = "nombre")
    val nombre: String,

    @ColumnInfo(name = "descripcion")
    val descripcion: String,

    @ColumnInfo(name = "ubicacion")
    val ubicacion: String,

    @ColumnInfo(name = "imagen")
    val imagen: String
)