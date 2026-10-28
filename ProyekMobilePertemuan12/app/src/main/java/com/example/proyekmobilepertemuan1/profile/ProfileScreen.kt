package com.example.proyekmobilepertemuan1.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyekmobilepertemuan1.ui.theme.ProyekMobilePertemuan1Theme

@Composable
fun ProfileScreen(
    onNavigateToGreeting: () -> Unit = {}
) {
    // BOX: Menempatkan Kartu tepat di tengah layar (Layering / Alignment)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            // COLUMN: Menyusun konten dari atas ke bawah secara vertikal
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Teks Nama
                Text(
                    text = "Nizar Rabbi Radliya",
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                // Teks Program Studi
                Text(
                    text = "D3 Manajemen Informatika",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(16.dp))
                // ROW 1: Menyusun Ikon dan Teks Email secara Horizontal
                // (Kiri ke Kanan)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Ikon Email",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "nizar@unikom.ac.id",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                // ROW 2: Menyusun Ikon dan Teks Telepon secara Horizontal
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Ikon Telepon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "+62 812-3456-7890",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                // Tombol Aksi
                Button(
                    onClick = { /* Aksi saat tombol diklik */ },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Kirim Pesan")
                }
                Spacer(modifier = Modifier.height(8.dp))
                // <-- 2. TAMBAHKAN TOMBOL UNTUK KEMBALI KE GREETING
                OutlinedButton(
                    onClick = onNavigateToGreeting,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("← Kembali ke Greeting")
                }
            }
        }
    }
}
// PREVIEW: Menampilkan tampilan langsung di Android Studio tanpa Emulator
@Preview(showBackground = true, showSystemUi = true, name = "Preview Profil")
@Composable
fun ProfileScreenPreview() {
    ProyekMobilePertemuan1Theme {
        ProfileScreen()
    }
}