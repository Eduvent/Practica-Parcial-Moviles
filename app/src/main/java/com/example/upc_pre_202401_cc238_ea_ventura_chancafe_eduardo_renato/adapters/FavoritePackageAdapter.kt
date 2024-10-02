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

class FavoritePackageAdapter(
    private var favorites: List<TourPackage>,
    private val listener: OnFavoriteClickListener
) : RecyclerView.Adapter<FavoritePackageAdapter.FavoritePackageViewHolder>() {

    inner class FavoritePackageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        private val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        private val ivImagen: ImageView = itemView.findViewById(R.id.ivImagen)
        private val btRemove: ImageButton = itemView.findViewById(R.id.btRemove)

        fun bind(tourPackage: TourPackage, listener: OnFavoriteClickListener) {
            tvNombre.text = tourPackage.nombre
            tvDescripcion.text = tourPackage.descripcion

            Picasso.get()
                .load(tourPackage.imagen)
                .into(ivImagen)

            btRemove.setOnClickListener {
                listener.onRemoveClick(tourPackage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePackageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.prototype_favorite_package, parent, false)
        return FavoritePackageViewHolder(view)
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: FavoritePackageViewHolder, position: Int) {
        holder.bind(favorites[position], listener)
    }

    fun updateFavorites(newFavorites: List<TourPackage>) {
        favorites = newFavorites
        notifyDataSetChanged()
    }

    interface OnFavoriteClickListener {
        fun onRemoveClick(tourPackage: TourPackage)
    }
}