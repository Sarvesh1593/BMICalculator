package com.mack.fasttrack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.mack.fasttrack.databinding.ActivityMainBinding
import com.mack.fasttrack.ui.MainScreen

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor= ContextCompat.getColor(this,R.color.Screen_background)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.Screen_background)
        binding.button.setOnClickListener {
            startActivity(Intent(this,MainScreen::class.java))
        }
    }
}