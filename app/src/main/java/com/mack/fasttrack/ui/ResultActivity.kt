package com.mack.fasttrack.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.mack.fasttrack.Graph.DonutChartBmi
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bmi = intent.getFloatExtra("bmi",0.0f)
        Log.d("BMI", "Received BMI value: $bmi")

        var bmiTxt = binding.bmiTextView
        bmiTxt.text = bmi.toString()

        val donutGraphBmi : DonutChartBmi = binding.bmiChart

        donutGraphBmi.setBmiValue(bmi)
    }
    private fun getBmiCategory(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi >= 18.5 && bmi < 29.5 -> "Normal"
            else -> "Overweight"
        }
    }
}