package com.mack.fasttrack.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityCustomSplashScreenBinding

class CustomSplashScreen : AppCompatActivity() {
    private lateinit var binding : ActivityCustomSplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomSplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            binding.bmiSplash.visibility = View.VISIBLE
            binding.bmiSplash.playAnimation()
            startActivity(Intent(this,MainScreen::class.java))
            finish()
        },3000)
    }
}