package com.example.materi4bagian2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var labelSpinner: Spinner
    private lateinit var familyCheckBox: CheckBox
    private lateinit var friendCheckBox: CheckBox
    private lateinit var simRadioGroup: RadioGroup
    private lateinit var saveButton: Button
    private var labelSelected:String = ""
    private fun initComponents() {
        nameEditText = findViewById(R.id.nameEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        labelSpinner = findViewById(R.id.labelSpinner)
        familyCheckBox = findViewById(R.id.familyCheckBox)
        friendCheckBox = findViewById(R.id.friendCheckBox)
        simRadioGroup = findViewById(R.id.simRadioButton)
        saveButton = findViewById(R.id.saveButton)
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
        val labelOptions = resources.getStringArray(R.array.label)
        if (labelSpinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, labelOptions
            )
            labelSpinner.adapter = adapter
            labelSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int,
                                            id: Long) {
                    labelSelected = labelOptions[position]
                }
                override fun onNothingSelected(parent: AdapterView<*>)
                {
                    // write code to perform some action
                }
            }
        }
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val label = labelSelected
            val group:StringBuffer = StringBuffer()
            if ((familyCheckBox != null) && (friendCheckBox != null)) {
                if (familyCheckBox.isChecked) { group.append("Family ")
                }
                if (friendCheckBox.isChecked) { group.append("Friends")
                }
            }
            val sim = when (simRadioGroup.checkedRadioButtonId) {
                R.id.sim1RadioButton -> "SIM 1"
                R.id.sim2RadioButton -> "SIM 2"
                else -> ""
            }
            val saveIntent = Intent(this, SaveActivity::class.java)
            saveIntent.putExtra("name", name)
            saveIntent.putExtra("phone", phone)
            saveIntent.putExtra("label", label)
            saveIntent.putExtra("group", group.toString())
            saveIntent.putExtra("sim", sim)
            startActivity(saveIntent)
        }
    }
}