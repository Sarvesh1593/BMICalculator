package com.mack.fasttrack.underweight

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityMealBinding

class MealActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var tipsAdapter: nutritionAdapter
    private lateinit var binding : ActivityMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_meal)

        recyclerView = binding.rcMeal
        recyclerView.layoutManager = LinearLayoutManager(this)

        val tipsList = generateNutritionTips()
        tipsAdapter = nutritionAdapter(tipsList)
        recyclerView.adapter = tipsAdapter

    }

    private fun generateNutritionTips(): List<NutritionModel> {
        return listOf(
            NutritionModel("Caloric Surplus", "Gradually increase your calorie intake to create a caloric surplus. This can be achieved by consuming more nutrient-dense foods."),
            NutritionModel("Protein-Rich Foods", "Include protein-rich foods like lean meats, poultry, fish, eggs, dairy products, legumes, and nuts to support muscle growth."),
            NutritionModel("Healthy Fats", "Incorporate healthy fats from sources such as avocados, nuts, seeds, and olive oil to add extra calories."),
            NutritionModel("Whole Grains", "Choose whole grains to provide energy and essential nutrients."),
            NutritionModel("Frequent Meals", "Have frequent, smaller meals throughout the day to make it easier to consume more calories."),
            NutritionModel("Strength Training", "Include strength training exercises to build muscle mass."),
            NutritionModel("Nutrient-Dense Snacks", "Snack on nutrient-dense options like yogurt, cheese, trail mix, and fruits.")
        )
    }
}