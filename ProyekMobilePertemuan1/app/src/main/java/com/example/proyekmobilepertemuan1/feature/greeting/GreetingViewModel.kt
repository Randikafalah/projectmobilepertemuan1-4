package com.example.proyekmobilepertemuan1.feature.greeting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.proyekmobilepertemuan1.data.model.Mahasiswa

class GreetingViewModel : ViewModel() {
    // State 1: Menampung teks input dari TextField
    var inputNama by mutableStateOf("")
        private set

    // State 2: Menampung data objek Mahasiswa yang akan ditampilkan
    var dataMahasiswa by mutableStateOf<Mahasiswa?>(null)
        private set

    // Event Handler saat teks diisi/diubah oleh pengguna
    fun onNamaChanged(namaBaru: String) {
        inputNama = namaBaru
    }

    // Event Handler saat tombol diklik
    fun sapaMahasiswa() {
        if (inputNama.isNotBlank()) {
            dataMahasiswa = Mahasiswa(nama = inputNama)
        }
    }
}