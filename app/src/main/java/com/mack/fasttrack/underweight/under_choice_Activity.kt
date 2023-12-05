package com.mack.fasttrack.underweight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityUnderChoiceBinding

class under_choice_Activity : AppCompatActivity() {
    private lateinit var binding : ActivityUnderChoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnderChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.exercise.setOnClickListener {
            startActivity(Intent(this,Under_ExerciseActivity::class.java))
        }
        binding.meal.setOnClickListener {
            startActivity(Intent(this,MealActivity::class.java))
        }
    }
}