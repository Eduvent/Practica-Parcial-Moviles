package com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.R
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.adapters.FavoritePackageAdapter
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.db.AppDatabase
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.models.TourPackage
import kotlinx.coroutines.*

class FavoritePackagesActivity : AppCompatActivity(), FavoritePackageAdapter.OnFavoriteClickListener {

    private lateinit var rvFavoritePackages: RecyclerView
    private lateinit var adapter: FavoritePackageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_packages)

        // Habilitar el botón de "regresar" en la barra de acción
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Inicializar RecyclerView
        rvFavoritePackages = findViewById(R.id.rvFavoritePackages)
        rvFavoritePackages.layoutManager = LinearLayoutManager(this)
        adapter = FavoritePackageAdapter(emptyList(), this)
        rvFavoritePackages.adapter = adapter

        // Cargar paquetes favoritos
        cargarFavoritos()
    }


    override fun onResume() {
        super.onResume()
        cargarFavoritos()
    }

    private fun cargarFavoritos() {
        lifecycleScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {
                val db = AppDatabase.getInstance(applicationContext)
                val tourPackageDao = db.getDao()
                tourPackageDao.getAll()
            }
            if (result.isNotEmpty()) {
                adapter.updateFavorites(result)
            } else {
                adapter.updateFavorites(emptyList())
                Toast.makeText(this@FavoritePackagesActivity, "No hay paquetes favoritos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRemoveClick(tourPackage: TourPackage) {
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val db = AppDatabase.getInstance(applicationContext)
                val tourPackageDao = db.getDao()
                tourPackageDao.delete(tourPackage)
            }
            Toast.makeText(this@FavoritePackagesActivity, "Paquete eliminado de favoritos", Toast.LENGTH_SHORT).show()
            cargarFavoritos()
        }
    }

}