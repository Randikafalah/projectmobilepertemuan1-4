package com.example.layoutmaterialform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.content.res.Configuration
import com.example.layoutmaterialform.ui.screen.FormScreen
import com.example.layoutmaterialform.ui.theme.LayoutMaterialFormTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Theme bawaan ini sudah mengelola peralihan Light Mode / Dark Mode secara otomatis
            LayoutMaterialFormTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FormScreen()
                }
            }
        }
    }
}
// 1. FUNGSI COMPOSABLE UTAMA (Penyatu Theme, Surface, dan Screen)
@Composable
fun MainContent() {
    LayoutMaterialFormTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FormScreen()
        }
    }
}
// 2. PREVIEW MODE TERANG (Light Mode)
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Main Activity - Light Mode"
)
@Composable
fun MainActivityPreview() {
    MainContent()
}
// 3. PREVIEW MODE GELAP (Dark Mode)
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Main Activity - Dark Mode"
)
@Composable
fun MainActivityDarkPreview() {
    MainContent()
}