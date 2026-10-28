package com.example.randika_mi1

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrof   it2.Response

class MainActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var listContact: RecyclerView
    private lateinit var fabCreate: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupList()
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        getContact()
    }

    private fun setupList() {
        listContact = findViewById(R.id.RecyclerViewContact)
        contactAdapter = ContactAdapter(arrayListOf(), object : ContactAdapter.OnAdapterListener {
            override fun onUpdate(contact: ReadModel.Data) {
                val intent = Intent(this@MainActivity, UpdateActivity::class.java)
                intent.putExtra("contact", contact)
                startActivity(intent)
            }

            override fun onDelete(contact: ReadModel.Data) {
                AlertDialog.Builder(this@MainActivity)
                    .setMessage("Yakin akan menghapus data ${contact.name}?")
                    .setTitle("Hapus Data")
                    .setPositiveButton("Hapus") { _, _ ->
                        api.delete(contact.id!!)
                            .enqueue(object : Callback<SubmitModel> {
                                override fun onResponse(
                                    call: Call<SubmitModel>,
                                    response: Response<SubmitModel>
                                ) {
                                    if (response.isSuccessful) {
                                        val submit = response.body()
                                        Toast.makeText(
                                            applicationContext,
                                            submit?.message,
                                            Toast.LENGTH_LONG
                                        ).show()
                                        getContact()
                                    }
                                }

                                override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                                    Toast.makeText(
                                        applicationContext,
                                        "Error: ${t.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                    }
                    .setNegativeButton("Batal") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        })
        listContact.adapter = contactAdapter
    }

    private fun setupListener() {
        fabCreate = findViewById(R.id.fabCreate)
        fabCreate.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getContact() {
        api.data().enqueue(object : Callback<ReadModel> {
            override fun onResponse(call: Call<ReadModel>, response: Response<ReadModel>) {
                if (response.isSuccessful) {
                    val listData = response.body()?.result ?: arrayListOf()
                    contactAdapter.setData(listData)
                }
            }

            override fun onFailure(call: Call<ReadModel>, t: Throwable) {
                Log.e("PPM", t.toString())
            }
        })
    }
}
