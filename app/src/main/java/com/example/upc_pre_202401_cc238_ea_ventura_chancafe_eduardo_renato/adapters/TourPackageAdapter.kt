package com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.R
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.models.TourPackage
import com.squareup.picasso.Picasso

class TourPackageAdapter(
    private var packages: List<TourPackage>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<TourPackageAdapter.TourPackageViewHolder>() {

    inner class TourPackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        private val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        private val tvUbicacion: TextView = itemView.findViewById(R.id.tvUbicacion)
        private val ivImagen: ImageView = itemView.findViewById(R.id.ivImagen)
        private val btFavorite: ImageButton = itemView.findViewById(R.id.btFavorite)

        fun bind(tourPackage: TourPackage, listener: OnItemClickListener) {
            tvNombre.text = tourPackage.nombre
            tvDescripcion.text = tourPackage.descripcion
            tvUbicacion.text = tourPackage.ubicacion

            Picasso.get()
                .load(tourPackage.imagen)
                .into(ivImagen)

            // Verificar si es favorito
            listener.isFavorite(tourPackage.idProducto ?: "") { isFav ->
                if (isFav) {
                    btFavorite.setImageResource(R.drawable.ic_favorite) // Ícono de favorito lleno
                } else {
                    btFavorite.setImageResource(R.drawable.ic_favorite_border) // Ícono de favorito vacío
                }
            }

            btFavorite.setOnClickListener {
                listener.onFavoriteClick(tourPackage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourPackageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_package, parent, false)
        return TourPackageViewHolder(view)
    }

    override fun getItemCount(): Int = packages.size

    override fun onBindViewHolder(holder: TourPackageViewHolder, position: Int) {
        holder.bind(packages[position], listener)
    }

    fun updatePackages(newPackages: List<TourPackage>) {
        packages = newPackages
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onFavoriteClick(tourPackage: TourPackage)
        fun isFavorite(idProducto: String, callback: (Boolean) -> Unit)
    }
}