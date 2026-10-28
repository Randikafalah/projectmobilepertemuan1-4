package com.example.layoutmaterialform.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layoutmaterialform.ui.theme.LayoutMaterialFormTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen() {
    // STATE: Untuk menampung input pengguna
    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isTA by remember { mutableStateOf(true) }
    var setuju by remember { mutableStateOf(false) }

    // STATE: Untuk validasi
    val isEmailValid = email.isNotEmpty() &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isErrorEmail = email.isNotEmpty() && !isEmailValid

    // STATE: Untuk Dropdown
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("D3 Manajemen Informatika", "S1 Sistem Informasi")
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    // SCAFFOLD: Kerangka utama halaman
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Form Pendaftaran") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Aksi simpan */ }) {
                Icon(Icons.Default.Add, contentDescription = "Simpan")
            }
        }
    ) { innerPadding ->
        // KONTEN UTAMA: Diberi innerPadding agar tidak tertutup AppBar/FAB
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()) // Agar form bisa discroll
        ) {
            // CARD: Mengelompokkan Form
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Data Mahasiswa",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary
                    )

                    // INPUT 1: TextField Biasa
                    OutlinedTextField(
                        value = nama,
                        onValueChange = { nama = it },
                        label = { Text("Nama Lengkap") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // INPUT 2: TextField dengan Validasi Error
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Alamat Email") },
                        isError = isErrorEmail, // Trigger merah jika format salah
                        supportingText = {
                            if (isErrorEmail) {
                                Text(
                                    "Format email tidak valid!",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // INPUT 3: Dropdown Menu (Pilih Program Studi)
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded }
                    ) {
                        OutlinedTextField(
                            value = selectedOptionText,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Program Studi") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            options.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        selectedOptionText = selectionOption
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }

                    // INPUT 4: Switch
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Tugas Akhir", modifier = Modifier.weight(1f))
                        Switch(
                            checked = isTA,
                            onCheckedChange = { isTA = it }
                        )
                    }

                    // INPUT 5: Checkbox
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = setuju,
                            onCheckedChange = { setuju = it }
                        )
                        Text(
                            text = "Saya menyetujui syarat & ketentuan",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    // TOMBOL SUBMIT
                    Button(
                        onClick = { /* Aksi submit */ },
                        enabled = setuju && nama.isNotBlank() && isEmailValid,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Kirim Pendaftaran")
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Form Screen - Light Mode"
)
@Composable
fun FormScreenPreview() {
    LayoutMaterialFormTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FormScreen()
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Form Screen - Dark Mode"
)
@Composable
fun FormScreenDarkPreview() {
    LayoutMaterialFormTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FormScreen()
        }
    }
}
