package com.example.randika_mi1

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateActivity : AppCompatActivity() {
    private val api by lazy { ApiRetrofit().endpoint }
    private lateinit var etName: EditText
    private lateinit var etNumber: EditText
    private lateinit var mbCreate: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupView()
        setupListener()
    }

    private fun setupView() {
        etName = findViewById(R.id.EditTextName)
        etNumber = findViewById(R.id.EditTextNumber)
        mbCreate = findViewById(R.id.mbCreate)
    }

    private fun setupListener() {
        mbCreate.setOnClickListener {
            val name = etName.text.toString()
            val number = etNumber.text.toString()

            if (name.isNotEmpty() && number.isNotEmpty()) {
                Log.i("PPM", "name: $name")
                Log.i("PPM", "number: $number")
                api.create(name, number).enqueue(object : Callback<SubmitModel> {
                    override fun onResponse(call: Call<SubmitModel>, response: Response<SubmitModel>) {
                        if (response.isSuccessful) {
                            val submit = response.body()
                            Toast.makeText(
                                applicationContext,
                                submit?.message ?: "Success",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<SubmitModel>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(
                    applicationContext,
                    "Name or Number cannot be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}