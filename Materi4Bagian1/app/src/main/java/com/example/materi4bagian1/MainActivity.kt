package com.example.materi4bagian1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    private lateinit var sendEditText: EditText
    private lateinit var sendingButton: Button
    private lateinit var phoneEditText: EditText
    private lateinit var dialButton: Button
    private fun initComponents() {
        sendEditText = findViewById(R.id.sendEditText)
        sendingButton = findViewById(R.id.sendingButton)
        phoneEditText = findViewById(R.id.phoneEditText)
        dialButton = findViewById(R.id.dialButton)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v,
                                                                             insets ->
            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top,
                systemBars.right, systemBars.bottom)
            insets
        }
        initComponents()
        sendingButton.setOnClickListener {
            val dataText = sendEditText.text.toString()
            val sendIntent = Intent(this, MessageActivity::class.java)
            sendIntent.putExtra("dataTextExtra", dataText)
            startActivity(sendIntent)
        }
        dialButton.setOnClickListener {
            val phoneNumber = phoneEditText.text.toString()
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:"+phoneNumber)
            startActivity(dialIntent)
        }
    }
    override fun onStart() {
        super.onStart()
        val toast = Toast.makeText(this, "Main Activity onStart",
            Toast.LENGTH_SHORT)
        toast.show()
    }
    override fun onResume() {
        super.onResume()
        val toast = Toast.makeText(this, "Main Activity onResume",
            Toast.LENGTH_SHORT)
        toast.show()
    }
    override fun onPause() {
        super.onPause()
        val toast = Toast.makeText(this, "Main Activity onPause",
            Toast.LENGTH_SHORT)
        toast.show()
    }
    override fun onStop() {
        super.onStop()
        val toast = Toast.makeText(
            this, "Main Activity onStop",
            Toast.LENGTH_SHORT
        )
        toast.show()
    }
    override fun onRestart() {
        super.onRestart()
        val toast = Toast.makeText(this, "Main Activity onRestart",
            Toast.LENGTH_SHORT)
        toast.show()
    }
    override fun onDestroy() {
        super.onDestroy()
        val toast = Toast.makeText(this, "Main Activity onDestroy",
            Toast.LENGTH_SHORT)
        toast.show()
    }

}