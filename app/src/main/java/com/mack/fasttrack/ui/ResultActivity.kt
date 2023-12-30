package com.mack.fasttrack.ui


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.mack.fasttrack.Graph.DonutChartBmi
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor= ContextCompat.getColor(this,R.color.Screen_background)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.Screen_background)

        val bmi = intent.getFloatExtra("bmi",0.1f)
        val height = intent.getStringExtra("height")
        val weight = intent.getStringExtra("weight")
        val age = intent.getStringExtra("age")
        Log.d("BMI", "Received BMI value: $bmi")
        Log.d("BMI",age.toString())

        var bmiTxt = binding.bmiTextView
        bmiTxt.text = bmi.toString()
        binding.age.text = "${age.toString()} years old"
        binding.weight.text ="${weight.toString()} kg"
        binding.height.text = "${height.toString()} cm"
        binding.bmiScore.text = bmi.toString()

        val donutGraphBmi : DonutChartBmi = binding.bmiChart

        // draw the bmi graph
        donutGraphBmi.setBmiValue(bmi)

        // get bmi category
        getBmiCategory(bmi)

        // set bmi text color
        setBmiTextColor(bmi)

        binding.btnRecalculate.setOnClickListener {
            startActivity(Intent(this,MainScreen::class.java))
        }
    }

    // function for set the bmi weighting status
    private fun getBmiCategory(bmiValue: Float): Any {
        return when {
            bmiValue > 0 && bmiValue < 18.5 -> binding.weightStatus.text="Under Weight"
            bmiValue > 18.5 && bmiValue < 24.9 -> binding.weightStatus.text="Normal"
            bmiValue > 24.9 && bmiValue < 29.9 -> binding.weightStatus.text="Over Weight"
            else -> binding.weightStatus.text="Obese"
        }
    }

    // set the bmi text color according to their bmi range
    private fun setBmiTextColor(bmiValue: Float) {
        val textColor: Int = when {
            bmiValue > 0 && bmiValue < 18.5 -> Color.YELLOW // Set your desired color for this range
            bmiValue >= 18.5 && bmiValue < 24.9 -> Color.GREEN
            bmiValue >= 24.9 && bmiValue < 29.9 -> Color.MAGENTA
            else -> Color.RED
        }

        binding.weightStatus.setTextColor(textColor)
    }

}