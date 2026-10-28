package com.example.mi_1_10924008 // Sesuaikan dengan nama package Anda

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        // Mengambil data yang dikirim dari MainActivity
        val nama = intent.getStringExtra("EXTRA_NAMA")
        val usia = intent.getStringExtra("EXTRA_USIA")
        val jabatan = intent.getStringExtra("EXTRA_JABATAN")
        val keahlian = intent.getStringExtra("EXTRA_KEAHLIAN")
        val gender = intent.getStringExtra("EXTRA_GENDER")

        // Memasukkan data ke dalam TextView di tabel
        findViewById<TextView>(R.id.tvResNama).text = nama
        findViewById<TextView>(R.id.tvResUsia).text = usia
        findViewById<TextView>(R.id.tvResJabatan).text = jabatan
        findViewById<TextView>(R.id.tvResKeahlian).text = keahlian
        findViewById<TextView>(R.id.tvResGender).text = gender
    }
}