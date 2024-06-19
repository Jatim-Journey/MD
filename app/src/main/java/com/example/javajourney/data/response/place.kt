package com.example.javajourney.data.response

import com.google.gson.annotations.SerializedName

data class GetPlace(
    @SerializedName("listPlace")
    val listPlace: List<Wisata> = emptyList()
)

data class Wisata(
    @SerializedName("place_id")
    val placeId: String,

    @SerializedName("place")
    val place: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("imageUrl")
    val imageUrl: String
)
