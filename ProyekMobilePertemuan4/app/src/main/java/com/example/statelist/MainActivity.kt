package com.example.statelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.content.res.Configuration
import com.example.statelist.feature.produk.ProdukScreen
import com.example.statelist.ui.theme.StateListTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProdukScreen()
                }
            }
        }
    }
}
@Preview(name = "Light Mode - MainActivity", showSystemUi = true, uiMode =
    Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "Dark Mode - MainActivity", showSystemUi = true, uiMode =
    Configuration.UI_MODE_NIGHT_YES)
@Composable
fun MainActivityPreview() {
    StateListTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ProdukScreen()
        }
    }
}