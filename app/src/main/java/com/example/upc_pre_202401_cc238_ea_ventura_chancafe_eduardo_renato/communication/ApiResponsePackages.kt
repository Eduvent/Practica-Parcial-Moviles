package com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.communication

import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.models.TourPackage
import com.google.gson.annotations.SerializedName

data class ApiResponsePackages(
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
            idProducto = idProducto ?: "N/A",
            nombre = nombre ?: "Nombre no disponible",
            descripcion = descripcion ?: "Descripción no disponible",
            ubicacion = ubicacion ?: "Ubicación no disponible",
            imagen = imagen ?: ""
        )
    }

}
