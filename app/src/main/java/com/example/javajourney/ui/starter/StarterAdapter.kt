import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.javajourney.R
import com.example.javajourney.data.response.Wisata

class StarterAdapter(private val context: Context, private val places: List<Wisata>) :
    RecyclerView.Adapter<StarterAdapter.PlaceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cards_place, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        holder.bind(place)
    }

    override fun getItemCount(): Int {
        return places.size
    }

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        private val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        private val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)

        fun bind(place: Wisata) {
            // Bind data to views
            tvName.text = place.place
            tvDescription.text = place.description

            // Load image using Glide
            Glide.with(context)
                .load(place.imageUrl)
                .centerCrop()
                .into(imgPhoto)
        }
    }
}
