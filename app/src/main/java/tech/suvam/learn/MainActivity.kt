package tech.suvam.learn

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val darkBtn = findViewById<Button>(R.id.darkBtn)
        val lightBtn = findViewById<Button>(R.id.lightBtn)
        val nextBtn = findViewById<Button>(R.id.next_btn)
        val layout = findViewById<LinearLayout>(R.id.main)
        // Retrieve the mode from the intent
        var mode = intent.getStringExtra("mode") ?: "light"

        // Set the background based on the mode
        if (mode == "dark") {
            layout.setBackgroundResource(R.color.black)
        } else {
            layout.setBackgroundResource(R.color.yellow_bg)
        }

        darkBtn.setOnClickListener {
            if (mode == "light") {
                Toast.makeText(applicationContext, "Switched to Dark Mode", Toast.LENGTH_SHORT).show()
                layout.setBackgroundResource(R.color.black)
                mode = "dark"
            } else {
                Toast.makeText(applicationContext, "Already in Dark Mode", Toast.LENGTH_SHORT).show()
            }
        }

        lightBtn.setOnClickListener {
            if (mode == "dark") {
                Toast.makeText(applicationContext, "Switched to Light Mode", Toast.LENGTH_SHORT).show()
                layout.setBackgroundResource(R.color.yellow_bg)
                mode = "light"
            } else {
                Toast.makeText(applicationContext, "Already in Light Mode", Toast.LENGTH_SHORT).show()
            }
        }

        // Carry the mode to the next activity
        nextBtn.setOnClickListener {
            intent = Intent(applicationContext, home::class.java)
            intent.putExtra("mode", mode)
            startActivity(intent)
            finish()
        }
    }
}