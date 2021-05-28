package com.madtutorial.savesimpledata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var deleteButton:Button

    private val sharedPreference: SharedPreferences by lazy {
        getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        deleteButton = findViewById(R.id.deleteButton)

        button.setOnClickListener {
            sharedPreference.edit {
                putString(SAMPLE_STRING, editText.text.toString())
            }

            setPreferenceTextIfAvailable()
        }

        deleteButton.setOnClickListener {
            sharedPreference.edit {
                remove(SAMPLE_STRING)
            }
            textView.text = getString(R.string.data_removed)
        }

        setPreferenceTextIfAvailable()
    }

    private fun setPreferenceTextIfAvailable() {
        if (sharedPreference.contains(SAMPLE_STRING)) {
            textView.text =
                getString(R.string.stored_text, sharedPreference.getString(SAMPLE_STRING, ""))
        }
    }

    companion object {
        const val PREF_NAME = "SIMPLEDATA"
        const val SAMPLE_STRING = "SAMPLE_STRING"
    }
}