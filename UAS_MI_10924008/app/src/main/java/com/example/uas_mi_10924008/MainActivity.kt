package com.example.uas_mi_10924008

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var rvMahasiswa: RecyclerView? = null
    private var fabAdd: FloatingActionButton? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMahasiswa = findViewById(R.id.rvMahasiswa)
        fabAdd = findViewById(R.id.fabAdd)
        progressBar = findViewById(R.id.progressBar)

        rvMahasiswa?.layoutManager = LinearLayoutManager(this)

        fabAdd?.setOnClickListener {
            startActivity(Intent(this, EditorActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        progressBar?.visibility = View.VISIBLE
        val apiService: ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)
        apiService.getMahasiswa().enqueue(object : Callback<ResponseModel> {
            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                progressBar?.visibility = View.GONE
                if (response.isSuccessful && response.body() != null) {
                    val dataList = response.body()?.data
                    if (dataList != null) {
                        val adapter = MahasiswaAdapter(this@MainActivity, dataList)
                        rvMahasiswa?.adapter = adapter
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Gagal mengambil data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                progressBar?.visibility = View.GONE
                Log.e("MainActivity", "loadData onFailure: ${t.message}", t)
                Toast.makeText(
                    this@MainActivity,
                    "Koneksi Gagal: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
