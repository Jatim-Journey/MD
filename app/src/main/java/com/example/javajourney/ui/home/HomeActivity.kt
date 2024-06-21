package com.example.javajourney.ui.home

import StarterAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.javajourney.data.response.Wisata
import com.example.javajourney.databinding.ActivityHomeBinding
import com.example.javajourney.ui.category.CategoryAlamActivity
import com.example.javajourney.ui.category.CategoryBahariActivity
import com.example.javajourney.ui.category.CategoryBudayaActivity
import com.example.javajourney.ui.category.CategoryReligionActivity
import com.example.javajourney.ui.category.CategoryTamanActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: StarterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvItem.layoutManager = LinearLayoutManager(this)

        val places = intent.getParcelableArrayListExtra<Wisata>("places")
        if (places != null) {
            adapter = StarterAdapter(this, places)
            binding.rvItem.adapter = adapter
        } else {
            Log.d("HomeActivity", "No places data received")
        }

        binding.btnBahari.setOnClickListener {
            val intent = Intent(this, CategoryBahariActivity::class.java)
            startActivity(intent)
        }
        binding.btnAlam.setOnClickListener {
            val intent = Intent(this, CategoryAlamActivity::class.java)
            startActivity(intent)
        }
        binding.btnBudaya.setOnClickListener {
            val intent = Intent(this, CategoryBudayaActivity::class.java)
            startActivity(intent)
        }
        binding.btnTaman.setOnClickListener {
            val intent = Intent(this, CategoryTamanActivity::class.java)
            startActivity(intent)
        }
        binding.btnReligion.setOnClickListener {
            val intent = Intent(this, CategoryReligionActivity::class.java)
            startActivity(intent)
        }
    }
}
