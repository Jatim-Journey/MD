package com.example.javajourney.ui.detail


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.javajourney.R
import com.example.javajourney.data.response.Wisata
import com.example.javajourney.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_PLACE = "extra_place"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val place = intent.getParcelableExtra<Wisata>(EXTRA_PLACE)
        if (place != null) {
            // Menggunakan binding untuk mengakses view
            with(binding) {
                Glide.with(this@DetailActivity)
                    .load(place.imageUrl)
                    .centerCrop()
                    .into(imageViewHeader)

                textViewTitle.text = place.place
                textViewLocation.text = place.city
                textViewDetails.text = place.description


                val travels = listOf(place.travel1, place.travel2, place.travel3, place.travel4)
                    .filter { travel -> travel.isNotEmpty() }
                    .joinToString ("\n\n")
                tvTravel.text = travels
                // Implementasi untuk RecyclerView dan lainnya
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}

