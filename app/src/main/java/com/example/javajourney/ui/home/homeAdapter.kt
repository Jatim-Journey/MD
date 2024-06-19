package com.example.javajourney.ui.home

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.javajourney.R
import com.example.javajourney.data.response.Wisata
import com.example.javajourney.ui.detail.DetailActivity

class HomeAdapter : PagingDataAdapter<Wisata, HomeAdapter.ListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cards_place, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val user = getItem(position)!!
        holder.bind(user)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                putExtra("img_item_photo", user.imageUrl)
                putExtra("tv_item_name", user.place)
                putExtra("tv_item_description", user.description)
            }
            val optionsCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeSceneTransitionAnimation(
                    holder.itemView.context as Activity,
                    Pair(holder.itemView.findViewById<ImageView>(R.id.img_item_photo), "storyImage"),
                    Pair(holder.itemView.findViewById<TextView>(R.id.tv_item_name), "storyTitle"),
                    Pair(holder.itemView.findViewById<TextView>(R.id.tv_item_description), "storyDesc")
                )
            holder.itemView.context.startActivity(intent, optionsCompat.toBundle())
        }
    }

    class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val storyTitle: TextView = view.findViewById(R.id.tv_item_name)
        private val storyDesc: TextView = view.findViewById(R.id.tv_item_description)
        private val imgStory: ImageView = view.findViewById(R.id.img_item_photo)

        fun bind(user: Wisata) {
            storyTitle.text = user.place
            storyDesc.text = user.description
            Glide.with(itemView.context).load(user.imageUrl).into(imgStory)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Wisata>() {
            override fun areContentsTheSame(oldItem: Wisata, newItem: Wisata): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: Wisata, newItem: Wisata): Boolean {
                return oldItem.placeId == newItem.placeId
            }
        }
    }
}
