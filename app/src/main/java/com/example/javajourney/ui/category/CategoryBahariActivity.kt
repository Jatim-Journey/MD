package com.example.javajourney.ui.category

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.javajourney.R
import com.example.javajourney.data.api.apiConfig
import com.example.javajourney.data.response.GetPlace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryBahariActivity : AppCompatActivity() {

    private lateinit var rvPlaces: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: CategoryAlamAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_alam)

        rvPlaces = findViewById(R.id.rvCategoryAlam)
        progressBar = findViewById(R.id.progressBar)

        adapter = CategoryAlamAdapter(this, emptyList())
        rvPlaces.layoutManager = LinearLayoutManager(this)
        rvPlaces.adapter = adapter

        fetchCategoryAlamData()
    }

    private fun fetchCategoryAlamData() {
        progressBar.visibility = ProgressBar.VISIBLE

        val apiService = apiConfig.getApiService()
        val call = apiService.getCategoryAlam("Wisata Bahari")

        call.enqueue(object : Callback<GetPlace> {
            override fun onResponse(call: Call<GetPlace>, response: Response<GetPlace>) {
                progressBar.visibility = ProgressBar.GONE
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("CategoryAlamActivity", "Raw response body: $body")
                    val places = body?.bahari
                    if (places != null && places.isNotEmpty()) {
                        adapter.updateData(places)
                    } else {
                        Log.d("CategoryAlamActivity", "No places found or list is empty")
                        Toast.makeText(this@CategoryBahariActivity, "No places found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.d("CategoryAlamActivity", "Failed to retrieve data: $errorBody")
                    Toast.makeText(this@CategoryBahariActivity, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GetPlace>, t: Throwable) {
                progressBar.visibility = ProgressBar.GONE
                Log.d("CategoryAlamActivity", "Request failed: ${t.message}")
                Toast.makeText(this@CategoryBahariActivity, "Request failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
