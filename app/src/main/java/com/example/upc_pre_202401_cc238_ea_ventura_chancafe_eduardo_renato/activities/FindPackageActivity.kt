package com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.R
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.adapters.TourPackageAdapter
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.communication.ApiResponsePackages
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.db.AppDatabase
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.models.TourPackage
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.network.RetrofitClient
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FindPackageActivity : AppCompatActivity(), TourPackageAdapter.OnItemClickListener {

    private lateinit var rvPackages: RecyclerView
    private lateinit var spinnerIdSitio: Spinner
    private lateinit var rgTipo: RadioGroup
    private lateinit var btnBuscar: Button
    private lateinit var adapter: TourPackageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_package)

        // Inicializar views
        spinnerIdSitio = findViewById(R.id.spinnerIdSitio)
        rgTipo = findViewById(R.id.rgTipo)
        btnBuscar = findViewById(R.id.btnBuscar)
        rvPackages = findViewById(R.id.rvPackages)

        // Configurar Spinner para IdSitio
        val sitios = listOf(
            "S001 - MachuPicchu",
            "S002 - Ayacucho",
            "S003 - Chichen Itza",
            "S004 - Cristo Redentor",
            "S005 - Islas Maldivas",
            "S006 - Muralla China"
        )
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, sitios)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerIdSitio.adapter = spinnerAdapter

        // Configuración del RecyclerView
        rvPackages.layoutManager = LinearLayoutManager(this)
        adapter = TourPackageAdapter(emptyList(), this)
        rvPackages.adapter = adapter

        // Manejo del botón Buscar
        btnBuscar.setOnClickListener {
            val selectedSitio = spinnerIdSitio.selectedItem.toString().split(" - ")[0] // Obtener el ID
            val tipo = when (rgTipo.checkedRadioButtonId) {
                R.id.rbViajes -> 1
                R.id.rbHospedaje -> 2
                else -> 1
            }

            buscarPaquetes(selectedSitio, tipo)
        }
    }

    private fun buscarPaquetes(sitio: String, tipo: Int) {
        val call = RetrofitClient.instance.getPackages(sitio, tipo)
        call.enqueue(object : Callback<ApiResponsePackages> {
            override fun onResponse(call: Call<ApiResponsePackages>, response: Response<ApiResponsePackages>) {
                if (response.isSuccessful) {
                    val packages = response.body()?.results?.map { it.toTourPackage() }
                    if (packages != null) {
                        adapter.updatePackages(packages)
                    } else {
                        Toast.makeText(this@FindPackageActivity, "No se encontraron paquetes", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@FindPackageActivity, "Error en la respuesta del servidor", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponsePackages>, t: Throwable) {
                Toast.makeText(this@FindPackageActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onFavoriteClick(tourPackage: TourPackage) {
        GlobalScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val db = AppDatabase.getInstance(applicationContext)
                val tourPackageDao = db.getDao()

                // Verificar si ya es favorito
                val favorite = tourPackageDao.getFavoriteById(tourPackage.idProducto!!)
                if (favorite != null) {
                    // Eliminar de favoritos
                    tourPackageDao.delete(favorite)
                } else {
                    // Agregar a favoritos
                    tourPackageDao.insertOne(tourPackage)
                }
            }
            // Actualizar el adaptador para reflejar el cambio
            adapter.notifyDataSetChanged()
        }
    }

    override fun isFavorite(idProducto: String, callback: (Boolean) -> Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            val favorite = withContext(Dispatchers.IO) {
                val db = AppDatabase.getInstance(applicationContext)
                val tourPackageDao = db.getDao()
                tourPackageDao.getFavoriteById(idProducto)
            }
            callback(favorite != null)
        }
    }
}