package com.mack.fasttrack.normalweightui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityNormalChoiceBinding

class Normal_choiceActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNormalChoiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNormalChoiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.meal.setOnClickListener {
            startActivity(Intent(this,NormalMealActivity::class.java))
        }
        binding.exercise.setOnClickListener {
            startActivity(Intent(this,NormalExerciseActivity::class.java))
        }
    }
}