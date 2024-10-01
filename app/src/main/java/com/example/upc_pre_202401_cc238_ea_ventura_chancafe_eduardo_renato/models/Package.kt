package com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_packages")
data class Package(
    @PrimaryKey val idProducto: String,
    val nombre: String,
    val descripcion: String,
    val ubicacion: String,
    val imagen: String
)