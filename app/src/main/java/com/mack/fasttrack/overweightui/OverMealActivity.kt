package com.mack.fasttrack.overweightui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityMealBinding
import com.mack.fasttrack.databinding.ActivityOverMealBinding
import com.mack.fasttrack.underweight.NutritionModel
import com.mack.fasttrack.underweight.nutritionAdapter

class OverMealActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var tipsAdapter: nutritionAdapter
    private lateinit var binding : ActivityOverMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityOverMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.mealOver
        recyclerView.layoutManager = LinearLayoutManager(this)

        val tipsList = generateNutritionTips()
        tipsAdapter = nutritionAdapter(tipsList)
        recyclerView.adapter = tipsAdapter
    }
    private fun generateNutritionTips(): List<NutritionModel> {
        return listOf(
            NutritionModel("Fruits and Vegetables", "Include a variety of colorful fruits and vegetables in your meals. They are rich in vitamins, minerals, and fiber."),
            NutritionModel("Whole Grains", "Choose whole grains such as brown rice, quinoa, whole wheat, and oats over refined grains."),
            NutritionModel("Protein", "Include lean sources of protein such as poultry, fish, tofu, legumes, and beans."),
            NutritionModel("Healthy Fats", "Incorporate sources of healthy fats, such as avocados, nuts, seeds, and olive oil."),
            NutritionModel("Limit Added Sugars", "Reduce the intake of sugary beverages, candies, and processed foods high in added sugars."),
            NutritionModel("Portion Control", "Be mindful of portion sizes to avoid overeating."),
            NutritionModel("Hydration", "Drink plenty of water throughout the day. Sometimes, our bodies can mistake thirst for hunger.")
        )
    }
}