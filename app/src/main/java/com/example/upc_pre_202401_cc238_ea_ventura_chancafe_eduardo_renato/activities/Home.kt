package com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.upc_pre_202401_cc238_ea_ventura_chancafe_eduardo_renato.R

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnFindPackage: Button = findViewById(R.id.btnFindPackage)
        val btnFavoritePackages: Button = findViewById(R.id.btnFavoritePackages)
        val ivBranding: ImageView = findViewById(R.id.ivBranding)

        btnFindPackage.setOnClickListener {
            val intent = Intent(this, FindPackageActivity::class.java)
            startActivity(intent)
        }

        btnFavoritePackages.setOnClickListener {
            val intent = Intent(this, FavoritePackagesActivity::class.java)
            startActivity(intent)
        }
    }
}