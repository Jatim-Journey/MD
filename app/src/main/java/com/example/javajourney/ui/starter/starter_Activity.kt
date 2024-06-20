package com.example.javajourney.ui.starter

import StarterAdapter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.javajourney.R
import com.example.javajourney.data.api.apiConfig
import com.example.javajourney.data.response.GetPlace
import com.example.javajourney.data.response.Wisata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class starter_Activity : AppCompatActivity() {

    private lateinit var searchBar: EditText
    private lateinit var searchButton: Button
    private lateinit var rvUsers: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: StarterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)

        searchBar = findViewById(R.id.search_bar)
        searchButton = findViewById(R.id.search_button)
        rvUsers = findViewById(R.id.rvUsers)
        progressBar = findViewById(R.id.progressBar)

        adapter = StarterAdapter(this, emptyList()) // Inisialisasi adapter dengan list kosong
        rvUsers.layoutManager = LinearLayoutManager(this)
        rvUsers.adapter = adapter

        searchButton.setOnClickListener {
            val userIdString = searchBar.text.toString()
            if (userIdString.isNotEmpty()) {
                val userId = userIdString.toIntOrNull()
                if (userId != null) {
                    performSearch(userId)
                } else {
                    Toast.makeText(this, "Invalid user ID", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a user ID", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performSearch(userId: Int) {
        progressBar.visibility = ProgressBar.VISIBLE

        val apiService = apiConfig.getApiService()
        val call = apiService.getChoice(userId)

        call.enqueue(object : Callback<GetPlace> {
            override fun onResponse(call: Call<GetPlace>, response: Response<GetPlace>) {
                progressBar.visibility = ProgressBar.GONE
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("starter_Activity", "Raw response body: $body")

                    val places = body?.listPlace
                    if (places != null && places.isNotEmpty()) {
                        Log.d("starter_Activity", "Places found: ${places.size}")
                        adapter.updateData(places)
                    } else {
                        Log.d("starter_Activity", "No places found or list is empty")
                        Toast.makeText(this@starter_Activity, "No places found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.d("starter_Activity", "Failed to retrieve data: $errorBody")
                    Toast.makeText(this@starter_Activity, "Failed to retrieve data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GetPlace>, t: Throwable) {
                progressBar.visibility = ProgressBar.GONE
                Log.d("starter_Activity", "Request failed: ${t.message}")
                Toast.makeText(this@starter_Activity, "Request failed: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
