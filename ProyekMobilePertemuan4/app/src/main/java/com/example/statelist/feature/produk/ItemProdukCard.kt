package com.example.statelist.feature.produk

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import android.content.res.Configuration
import androidx.compose.ui.unit.dp
import com.example.statelist.data.model.Produk
import com.example.statelist.ui.theme.StateListTheme
// Komponen terpisah untuk mengatur tampilan satu baris item (Penerapan Custom Card Item)
@Composable
fun ItemProdukCard(
    produk: Produk,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = produk.nama,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Harga: Rp ${produk.harga}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
@Preview(name = "Light Mode - Item Card", showBackground = true, uiMode =
    Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode - Item Card", showBackground = true, uiMode =
    Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ItemProdukCardPreview() {
    StateListTheme {
        Surface {
            ItemProdukCard(
                produk = Produk(id = 1, nama = "Mouse Wireless", harga =
                    150000)
            )
        }
    }
}