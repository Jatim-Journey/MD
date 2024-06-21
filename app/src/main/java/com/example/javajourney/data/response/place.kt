package com.example.javajourney.data.response
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GetPlace(
    @SerializedName("listPlaces")
    val listPlace: List<Wisata> = emptyList(),

    @SerializedName("Wisata Alam")
    val places: List<Wisata>,

    @SerializedName("Wisata Bahari")
    val bahari: List<Wisata>,

    @SerializedName("Budaya")
    val budaya: List<Wisata>,

    @SerializedName("Taman Hiburan")
    val taman: List<Wisata>,

    @SerializedName("Wisata Religi")
    val religion: List<Wisata>,
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
    val imageUrl: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("rating")
    val rating: String,

    @SerializedName("travel1")
    val travel1: String,

    @SerializedName("travel2")
    val travel2: String,

    @SerializedName("travel3")
    val travel3: String,

    @SerializedName("travel4")
    val travel4: String,

    @SerializedName("phones")
    val phone: String,

    @SerializedName("sites")
    val sites: String,

    // Tambahkan properti lain yang diperlukan
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(placeId)
        parcel.writeString(place)
        parcel.writeString(city)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
        parcel.writeString(price)
        parcel.writeString(rating)
        parcel.writeString(travel1)
        parcel.writeString(travel2)
        parcel.writeString(travel3)
        parcel.writeString(travel4)
        parcel.writeString(phone)
        parcel.writeString(sites)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Wisata> {
        override fun createFromParcel(parcel: Parcel): Wisata {
            return Wisata(parcel)
        }

        override fun newArray(size: Int): Array<Wisata?> {
            return arrayOfNulls(size)
        }
    }
}