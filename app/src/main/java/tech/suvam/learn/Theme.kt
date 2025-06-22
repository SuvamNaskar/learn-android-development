package tech.suvam.learn

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Theme : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_NAME = "theme_preference"
    private val MODE_KEY = "theme_mode"
    private lateinit var layout: LinearLayout
    private var currentMode: String = "light" // Default to light

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        layout = findViewById(R.id.main)

        // Retrieve the saved mode, defaulting to "light" if not found
        currentMode = sharedPreferences.getString(MODE_KEY, "light") ?: "light"

        // Set the initial background based on the saved mode
        updateThemeUI(currentMode)

        val darkBtn = findViewById<Button>(R.id.darkBtn)
        val lightBtn = findViewById<Button>(R.id.lightBtn)
        val nextBtn = findViewById<Button>(R.id.next_btn)

        darkBtn.setOnClickListener {
            if (currentMode == "light") {
                Toast.makeText(applicationContext, "Switched to Dark Mode", Toast.LENGTH_SHORT).show()
                updateThemeUI("dark")
                saveThemePreference("dark")
            } else {
                Toast.makeText(applicationContext, "Already in Dark Mode", Toast.LENGTH_SHORT).show()
            }
        }

        lightBtn.setOnClickListener {
            if (currentMode == "dark") {
                Toast.makeText(applicationContext, "Switched to Light Mode", Toast.LENGTH_SHORT).show()
                updateThemeUI("light")
                saveThemePreference("light")
            } else {
                Toast.makeText(applicationContext, "Already in Light Mode", Toast.LENGTH_SHORT).show()
            }
        }

        nextBtn.setOnClickListener {
            val intent = Intent(applicationContext, Home::class.java)
            intent.putExtra("mode", currentMode) // Pass the current mode
            startActivity(intent)
            finish()
        }
    }

    private fun updateThemeUI(mode: String) {
        currentMode = mode // Update the current mode
        if (mode == "dark") {
            layout.setBackgroundResource(R.color.black)
        } else {
            layout.setBackgroundResource(R.color.yellow_bg)
        }
    }

    private fun saveThemePreference(mode: String) {
        with(sharedPreferences.edit()) {
            putString(MODE_KEY, mode)
            apply() // Use apply() for asynchronous saving
        }
    }
}