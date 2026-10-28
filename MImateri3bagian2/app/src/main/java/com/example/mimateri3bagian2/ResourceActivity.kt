package com.example.mimateri3bagian2
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class ResourceActivity : AppCompatActivity() {
    private lateinit var appNameTextView: TextView
    private fun initComponents() {
        appNameTextView = findViewById(R.id.appNameTextView)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resource)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v,
                                                                             insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top,
                systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        appNameTextView.text = resources.getString(R.string.app_name)
        resources.getStringArray(R.array.prodi).forEach {
            Log.i("StringArray", it)
        }
        val dataJson = assets.open("data.json")
            .bufferedReader()
            .use { it.readText() }
        Log.i("ASSETS", dataJson)
        val rawDataJson = resources.openRawResource(R.raw.data)
            .bufferedReader()
            .use { it.readText() }
        Log.i("RAW", rawDataJson)
    }
}