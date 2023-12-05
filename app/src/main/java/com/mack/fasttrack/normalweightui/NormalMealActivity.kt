package com.mack.fasttrack.normalweightui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityMealBinding
import com.mack.fasttrack.databinding.ActivityNormalMealBinding
import com.mack.fasttrack.underweight.NutritionModel
import com.mack.fasttrack.underweight.nutritionAdapter

class NormalMealActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var tipsAdapter: nutritionAdapter
    private lateinit var binding : ActivityNormalMealBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNormalMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.normalMealRec
        recyclerView.layoutManager = LinearLayoutManager(this)

        val tipsList = generateNutritionTips()
        tipsAdapter = nutritionAdapter(tipsList)
        recyclerView.adapter = tipsAdapter
    }
    private fun generateNutritionTips(): List<NutritionModel> {
        return listOf(
            NutritionModel("Balanced Diet", "Continue to eat a balanced diet with a mix of carbohydrates, proteins, and fats."),
            NutritionModel("Portion Control", "Be mindful of portion sizes to maintain a healthy balance between energy intake and expenditure."),
            NutritionModel("Regular Meals", "Stick to regular meal times to maintain a consistent eating pattern."),
            NutritionModel("Hydration", "Stay well-hydrated by drinking an adequate amount of water."),
            NutritionModel("Nutrient-Dense Snacks", "Snack on nutrient-dense options like yogurt, cheese, trail mix, and fruits.")
        )
    }
}