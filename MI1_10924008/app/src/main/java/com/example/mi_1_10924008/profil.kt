package com.example.mi_1_10924008 // Pastikan package ini sesuai

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class profil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Menghubungkan ke layout activity_profile.xml yang baru
        setContentView(R.layout.activity_profile)
    }
}