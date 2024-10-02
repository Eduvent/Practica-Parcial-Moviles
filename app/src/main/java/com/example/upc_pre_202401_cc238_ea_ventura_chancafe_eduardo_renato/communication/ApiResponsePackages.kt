package com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.communication

import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.models.TourPackage
import com.google.gson.annotations.SerializedName

data class ApiResponsePackages(
    @SerializedName("results")
    val results: List<TourPackageResponse>
)

data class TourPackageResponse(
    @SerializedName("idProducto")
    val idProducto: String,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("descripcion")
    val descripcion: String,

    @SerializedName("ubicacion")
    val ubicacion: String,

    @SerializedName("imagen")
    val imagen: String
) {
    fun toTourPackage(): TourPackage {
        return TourPackage(
            idProducto = idProducto,
            nombre = nombre,
            descripcion = descripcion,
            ubicacion = ubicacion,
            imagen = imagen
        )
    }
}
