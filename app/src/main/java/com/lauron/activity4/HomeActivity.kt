package com.lauron.activity4

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lauron.activity4.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE)

        // Load data from Local Storage
        loadSettings()

        // Set up Save Button
        binding.save.setOnClickListener {
            saveSettings()
        }
    }

    private fun loadSettings() {
        // Get the saved values from Local Storage
        val editEmailValue = sharedPreferences.getString("editEmailValue", "")
        val editNameValue = sharedPreferences.getString("editNameValue", "")
        val radioButtonLightValue = sharedPreferences.getBoolean("radioButtonLightValue", false)
        val radioButtonDarkValue = sharedPreferences.getBoolean("radioButtonDarkValue", false)
        val checkBoxChecked = sharedPreferences.getBoolean("checkBoxChecked", false)

        // Update the Form's state with the saved values
        binding.email.setText(editEmailValue)
        binding.name.setText(editNameValue)
        binding.light.isChecked = radioButtonLightValue
        binding.dark.isChecked = radioButtonDarkValue
        binding.notifCheck.isChecked = checkBoxChecked
    }

    private fun saveSettings() {
        // Get the current values of the Form
        val editEmailValue = binding.email.text.toString()
        val editNameValue = binding.name.text.toString()
        val radioButtonLightValue = binding.light.isChecked
        val radioButtonDarkValue = binding.dark.isChecked
        val checkBoxChecked = binding.notifCheck.isChecked

        // Save the values to Local Storage
        sharedPreferences.edit()
            .putString("editEmailValue", editEmailValue)
            .putString("editNameValue", editNameValue)
            .putBoolean("radioButtonLightValue", radioButtonLightValue)
            .putBoolean("radioButtonDarkValue", radioButtonDarkValue)
            .putBoolean("checkBoxChecked", checkBoxChecked)
            .apply()
    }
}