package com.example.statelist.feature.produk

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.statelist.data.model.Produk
import com.example.statelist.data.model.dummyProduk
import com.example.statelist.ui.theme.StateListTheme
@Composable
fun ProdukScreen(
    modifier: Modifier = Modifier
) {
    // State 1: Menampung daftar data produk yang dapat berubah dinamis
    var daftarProduk by remember { mutableStateOf(dummyProduk) }
    // State 2: Menampung nilai input form (rememberSaveable agar tahan rotasi layar)
    var namaInput by rememberSaveable { mutableStateOf("") }
    var hargaInput by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Manajemen Data Produk",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Form Input Nama Produk
        OutlinedTextField(
            value = namaInput,
            onValueChange = { namaInput = it },
            label = { Text("Nama Produk") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Form Input Harga Produk
        OutlinedTextField(
            value = hargaInput,
            onValueChange = { input ->
                // Validasi agar hanya karakter angka yang bisa diketik
                if (input.all { it.isDigit() }) {
                    hargaInput = input
                }
            },
            label = { Text("Harga Produk (Rp)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Tombol Tambah Data (Event)
        Button(
            onClick = {
                // Penerapan Event Handling
                if (namaInput.isNotBlank() && hargaInput.isNotBlank()) {
                    val produkBaru = Produk(
                        id = (daftarProduk.maxOfOrNull { it.id } ?: 0) + 1,
                        nama = namaInput,
                        harga = hargaInput.toIntOrNull() ?: 0
                    )
// Mengubah State daftarProduk untuk memicu Recomposition
                    daftarProduk = daftarProduk + produkBaru
                    // Reset form input
                    namaInput = ""
                    hargaInput = ""

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan Data")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Daftar Produk (${daftarProduk.size})",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Penerapan LazyColumn untuk me-render daftar data yang efisien
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = daftarProduk,
                key = { it.id }
            ) { produk ->
                ItemProdukCard(produk = produk)
            }
        }
    }
}
@Preview(name = "Light Mode - Produk Screen", showSystemUi = true, uiMode =
    Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode - Produk Screen", showSystemUi = true, uiMode =
    Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProdukScreenPreview() {
    StateListTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ProdukScreen()
        }
    }
}

