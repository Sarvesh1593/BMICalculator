package com.mack.fasttrack.overweightui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mack.fasttrack.databinding.ActivityOverChoiceBinding

class Over_choiceActivity : AppCompatActivity() {
    private lateinit var binding : ActivityOverChoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOverChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.meal.setOnClickListener {
            startActivity(Intent(this,OverMealActivity::class.java))
        }
        binding.exercise.setOnClickListener {
            startActivity(Intent(this,OverExerciseActivity::class.java))
        }
    }
}