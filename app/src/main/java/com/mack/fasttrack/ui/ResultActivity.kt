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
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityResultBinding
import com.mack.fasttrack.normalweightui.Normal_choiceActivity
import com.mack.fasttrack.overweightui.Over_choiceActivity
import com.mack.fasttrack.underweight.under_choice_Activity

class ResultActivity : AppCompatActivity() {
    private lateinit var binding : ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bmi = intent.getFloatExtra("bmi",0.0f)
        Log.d("BMI", "Received BMI value: $bmi")

        if(bmi < 18.5){
            binding.btnUnderWeight.visibility = View.VISIBLE
            binding.btnNormalWeight.visibility = View.GONE
            binding.btnOverWeight.visibility = View.GONE
        }else if(bmi >= 18.5 && bmi < 29.5){
            binding.btnUnderWeight.visibility = View.GONE
            binding.btnNormalWeight.visibility = View.VISIBLE
            binding.btnOverWeight.visibility = View.GONE
        }else{
            binding.btnUnderWeight.visibility = View.GONE
            binding.btnNormalWeight.visibility = View.GONE
            binding.btnOverWeight.visibility = View.VISIBLE
        }
        val lineChart : LineChart = binding.bmiChart
        setUpLineChart(lineChart,bmi)

        binding.btnUnderWeight.setOnClickListener {
            startActivity(Intent(this,under_choice_Activity::class.java))
        }
        binding.btnNormalWeight.setOnClickListener {
            startActivity(Intent(this,Normal_choiceActivity::class.java))
        }
        binding.btnOverWeight.setOnClickListener {
            startActivity(Intent(this,Over_choiceActivity::class.java))
        }
        binding.btnRecalculate.setOnClickListener {
            startActivity(Intent(this,MainScreen::class.java))
        }
    }
    private fun setUpLineChart(lineChart: LineChart, bmi: Float?) {
        val entries: ArrayList<Entry> = ArrayList()

        // Start with an initial value of 0 for the line
        val initialBmi = 0f
        entries.add(Entry(0f, initialBmi))

        if (bmi != null) {
            // Add the final BMI value to create the increasing effect
            entries.add(Entry(1f, bmi))
        }

        val lineDataSet = LineDataSet(entries, "BMI")
        lineDataSet.color = resources.getColor(R.color.selected_color)
        lineDataSet.setDrawCircles(true)   // Disable data points
        lineDataSet.setDrawCircleHole(true) // Disable circle holes
        lineDataSet.lineWidth = 2f          // Set line width

        // Animate the line to make it appear as increasing
        lineChart.animateX(1500) // Adjust the duration as needed

        val lineData = LineData(lineDataSet)
        lineChart.data = lineData
        // Customize the chart appearance as needed
        lineChart.description.isEnabled = false // Disable chart description
        lineChart.legend.isEnabled = false      // Disable legend
        lineChart.axisRight.isEnabled = false   // Disable right Y-axis

        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.invalidate()

        // Update the text view based on BMI category
        val bmiCategoryTextView: TextView = findViewById(R.id.bmiCategoryTextView) // Replace with your TextView's ID
        if (bmi != null) {
            val bmiCategory = getBmiCategory(bmi)
            bmiCategoryTextView.text = bmiCategory
        }
    }

    private fun getBmiCategory(bmi: Float): String {
        return when {
            bmi < 18.5 -> "Underweight"
            bmi >= 18.5 && bmi < 29.5 -> "Normal"
            else -> "Overweight"
        }
    }

}