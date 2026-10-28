package com.example.proyekmobilepertemuan1.feature.greeting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.proyekmobilepertemuan1.ui.theme.ProyekMobilePertemuan1Theme

@Composable
fun GreetingScreen(
    viewModel: GreetingViewModel = viewModel(),
    onNavigateToProfile: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Modern Android Development",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Arsitektur MVVM & Package by Feature",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.height(24.dp))
        // INTERAKSI 1: Input Form oleh User
        OutlinedTextField(
            value = viewModel.inputNama,
            onValueChange = { viewModel.onNamaChanged(it) },
            label = { Text("Masukkan Nama Anda") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(12.dp))
        // INTERAKSI 2: Tombol Aksi
        Button(
            onClick = { viewModel.sapaMahasiswa() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sapa Saya")
        }
        Spacer(modifier = Modifier.height(16.dp))
        // <-- TAMBAHKAN TOMBOL UNTUK PINDAH KE PROFILE (untuk pertemuan 2)
        OutlinedButton(
            onClick = onNavigateToProfile,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ke Halaman Profil ➔")
        }
        Spacer(modifier = Modifier.height(24.dp))
        // REAKSI VIEW: Menampilkan data Model setelah diproses oleh ViewModel
        viewModel.dataMahasiswa?.let { mahasiswa ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Selamat Datang, ${mahasiswa.nama}! 👋",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Peran: ${mahasiswa.peran}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Tampilan Awal Greeting Screen"
)
@Composable
fun GreetingScreenPreview() {
    ProyekMobilePertemuan1Theme {
        GreetingScreen()
    }
}