package tech.suvam.learn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve the mode from the intent
        val mode = intent.getStringExtra("mode") ?: "light"

        // Set the background based on the mode
        val layout = findViewById<LinearLayout>(R.id.main)
        if (mode == "dark") {
            layout.setBackgroundResource(R.color.black)
        } else {
            layout.setBackgroundResource(R.color.yellow_bg)
        }

        // if in dark mode, set text color to white
        if (mode == "dark") {
            findViewById<TextView>(R.id.textView).setTextColor(ContextCompat.getColor(this, R.color.white))
        } else {
            findViewById<TextView>(R.id.textView).setTextColor(ContextCompat.getColor(this, R.color.black))
        }

        val backbtn = findViewById<Button>(R.id.backbtn)
        val webBtn = findViewById<Button>(R.id.webBtn)
        val cameraBtn = findViewById<Button>(R.id.cameraBtn)
        val InAppWebBtn = findViewById<Button>(R.id.inAppWebBtn)

        backbtn.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("mode", mode) // Carry the mode back to MainActivity
            startActivity(intent)
            finish()
        }

        InAppWebBtn.setOnClickListener {
            val intent = Intent(applicationContext, WebAcitivity::class.java)
            startActivity(intent)
        }

        webBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://suvam.tech")
            startActivity(intent)
        }

        cameraBtn.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }
    }
}