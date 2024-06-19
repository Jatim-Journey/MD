package com.example.javajourney.ui.home

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.javajourney.R
import com.example.javajourney.data.api.apiConfig
import com.example.javajourney.data.response.GetPlace
import com.example.javajourney.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvItemAdapter: RecyclerView
    private lateinit var adapter: HomeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = HomeAdapter()
        rvItemAdapter = findViewById(R.id.rvItem)
        rvItemAdapter.setHasFixedSize(true)

        showList()

    }


    private fun showList() {
        val layoutManager = LinearLayoutManager(binding.rvItem.context)
        binding.rvItem.layoutManager = layoutManager

        val client = apiConfig.getApiService().getPlaces()
        client.enqueue(object : Callback<GetPlace> {
            override fun onResponse(
                call: Call<GetPlace>,
                response: Response<GetPlace>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        loadStory()
                    } else {
                        Toast.makeText(
                            this@HomeActivity,
                            "Error: ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.e(TAG, "onFailure: ${response.message()}")
                    }
                } else {
                    Toast.makeText(
                        this@HomeActivity,
                        "Error: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GetPlace>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
        val adapter = HomeAdapter()

    }

    private fun loadStory() {
        val adapter = HomeAdapter()
        binding.rvItem.adapter = adapter

    }

}