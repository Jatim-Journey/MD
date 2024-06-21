package com.example.javajourney.ui.detail

import android.os.Bundle
import android.text.util.Linkify
import androidx.appcompat.app.AppCompatActivity
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
                textViewPriceIndicator.text = place.price

                if (place.sites.isNotEmpty()) {
                    Sites.text = place.sites
                    Linkify.addLinks(Sites, Linkify.WEB_URLS)
                } else {
                    Sites.text = "Tidak ada informasi tertera"
                }

                Phone.text = if (place.phone.isNotEmpty()) place.phone else "Tidak ada informasi tertera"

                val travels = listOf(place.travel1, place.travel2, place.travel3, place.travel4)
                    .filter { travel -> travel.isNotEmpty() }
                    .joinToString ("\n\n")
                tvTravel.text = travels

                Linkify.addLinks(tvTravel, Linkify.WEB_URLS)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
