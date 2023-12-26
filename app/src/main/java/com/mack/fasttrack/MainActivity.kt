package com.mack.fasttrack

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mack.fasttrack.databinding.ActivityMainBinding
import com.mack.fasttrack.ui.CustomSplashScreen
import com.mack.fasttrack.ui.MainScreen

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            installSplashScreen()
            Thread.sleep(3000)
            startActivity(Intent(this,MainScreen::class.java))
            finish()
        }else{
            startActivity(Intent(this,CustomSplashScreen::class.java))
        }
        setContentView(binding.root)
        window.statusBarColor= ContextCompat.getColor(this,R.color.Screen_background)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.Screen_background)
    }
}