package com.example.mi_1_10924008

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {

    // Deklarasi variabel dengan properti Kotlin
    private lateinit var etNama: EditText
    private lateinit var etUsia: EditText
    private lateinit var spnJabatan: Spinner
    private lateinit var cbProg: CheckBox
    private lateinit var cbDb: CheckBox
    private lateinit var cbNet: CheckBox
    private lateinit var rgGender: RadioGroup
    private lateinit var btnSimpan: Button
    private lateinit var btnProfil: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Inisialisasi ID
        etNama = findViewById(R.id.etNama)
        etUsia = findViewById(R.id.etUsia)
        spnJabatan = findViewById(R.id.spnJabatan)
        cbProg = findViewById(R.id.cbProg)
        cbDb = findViewById(R.id.cbDb)
        cbNet = findViewById(R.id.cbNet)
        rgGender = findViewById(R.id.rgGender)
        btnSimpan = findViewById(R.id.btnSimpan)
        btnProfil = findViewById(R.id.btnProfil)

        // 2. Logika Tombol Simpan
        btnSimpan.setOnClickListener {
            val nama = etNama.text.toString()
            val usia = etUsia.text.toString()
            val jabatan = spnJabatan.selectedItem.toString()

            if (nama.isEmpty() || usia.isEmpty()) {
                Toast.makeText(this, "Nama dan Usia harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Ambil data Keahlian (Checkbox)
            val keahlian = StringBuilder()
            if (cbProg.isChecked) keahlian.append("Programming, ")
            if (cbDb.isChecked) keahlian.append("Database, ")
            if (cbNet.isChecked) keahlian.append("Networking, ")

            // Ambil data Gender (RadioGroup)
            val selectedId = rgGender.checkedRadioButtonId
            var gender = "Tidak dipilih"
            if (selectedId != -1) {
                val rb = findViewById<RadioButton>(selectedId)
                gender = rb.text.toString()
            }

            // Pindah ke DisplayActivity (Poin 2)
            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra("EXTRA_NAMA", nama)
            intent.putExtra("EXTRA_USIA", usia)
            intent.putExtra("EXTRA_JABATAN", jabatan)
            intent.putExtra("EXTRA_KEAHLIAN", keahlian.toString().removeSuffix(", "))
            intent.putExtra("EXTRA_GENDER", gender)
            startActivity(intent)
        }

        // 3. Tombol Profil (Poin 3)
        btnProfil.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}