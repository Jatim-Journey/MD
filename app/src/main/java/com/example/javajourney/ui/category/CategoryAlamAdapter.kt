package com.example.javajourney.ui.category

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.javajourney.R
import com.example.javajourney.data.response.Wisata
import com.example.javajourney.ui.detail.DetailActivity

class CategoryAlamAdapter(private val context: Context, private var places: List<Wisata>) :
    RecyclerView.Adapter<CategoryAlamAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cards_place, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        holder.bind(place)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_PLACE, place)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return places.size
    }

    fun updateData(newPlaces: List<Wisata>) {
        this.places = newPlaces
        notifyDataSetChanged()
        Log.d("StarterAdapter", "Data updated, new size: ${places.size}")
    }

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        private val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        private val tvRating: TextView = itemView.findViewById(R.id.textViewRatingValue)
        private val tvPrice: TextView = itemView.findViewById(R.id.textViewPriceIndicator)
        private val tvCity: TextView = itemView.findViewById(R.id.tv_asal)

        fun bind(place: Wisata) {
            // Bind data to views
            tvName.text = place.place
            tvRating.text = place.rating
            tvPrice.text = place.price
            tvCity.text = place.city

            // Load image using Glide or set default image
            if (!place.imageUrl.isNullOrBlank()) {
                Glide.with(context)
                    .load(place.imageUrl)
                    .centerCrop()
                    .into(imgPhoto)
            } else {
                // Set default image if no imageUrl is provided
                imgPhoto.setImageResource(R.drawable.baseline_person_24)
            }
        }
    }
}
