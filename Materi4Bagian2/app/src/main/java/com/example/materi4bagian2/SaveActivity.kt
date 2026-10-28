package com.example.materi4bagian2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SaveActivity : AppCompatActivity() {
    private lateinit var dataTextView: TextView
    private fun initComponents() {
        dataTextView = findViewById(R.id.dataTextView)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_save)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v,
                                                                             insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top,
                systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        val name = getIntent().getStringExtra("name")
        val phone = getIntent().getStringExtra("phone")
        val label = getIntent().getStringExtra("label")
        val group = getIntent().getStringExtra("group")
        val sim = getIntent().getStringExtra("sim")
        dataTextView.text = name + " - " + phone + " - " + label + " -" + group + " - " + sim
    }
}