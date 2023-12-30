package com.mack.fasttrack.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.mack.fasttrack.R
import com.mack.fasttrack.databinding.ActivityMainScreenBinding

class MainScreen : AppCompatActivity() {
    private lateinit var binding : ActivityMainScreenBinding
    private  var currentWeight =0
    private  var currentHeight =0
    private  var currentAge = 0
    private lateinit var weight: EditText
    private lateinit var height: EditText
    private lateinit var age: EditText
    private lateinit var btnWeightPlus: ImageButton
    private lateinit var btnWeightMinus: ImageButton
    private lateinit var btnHeightPlus: ImageButton
    private lateinit var btnHeightMinus: ImageButton
    private lateinit var btnAgePlus : ImageButton
    private lateinit var btnAgeMinus : ImageButton

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor= ContextCompat.getColor(this,R.color.Screen_background)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.Screen_background)


        weight = binding.editWeight
        height = binding.tvHeight
        age = binding.edtAge
        btnWeightPlus = binding.weightIncrease
        btnWeightMinus = binding.weightDecrease
        btnHeightPlus = binding.tvHeightIncrease
        btnHeightMinus = binding.tvHeightDecrease
        btnAgePlus = binding.ageIncrease
        btnAgeMinus = binding.ageDecrease


        val animation: Animation = AnimationUtils.loadAnimation(this, R.anim.cv_pop)
            binding.cvMale.setOnClickListener {
                binding.maleSelection.setImageResource(R.drawable.selected_bg)
                binding.femaleSelection.setImageResource(0)
                it.elevation = resources.getDimension(R.dimen.card_elevation_popup)
                it.startAnimation(animation)
            }
            binding.cvFemale.setOnClickListener {
                binding.femaleSelection.setImageResource(R.drawable.selected_bg)
                binding.maleSelection.setImageResource(0)
                it.elevation = resources.getDimension(R.dimen.card_elevation_popup)
                it.startAnimation(animation)
            }
        weight.text = Editable.Factory.getInstance().newEditable(currentWeight.toString())
        height.text = Editable.Factory.getInstance().newEditable(currentHeight.toString())
        age.text = Editable.Factory.getInstance().newEditable(currentAge.toString())

        setupButtonWeight(btnWeightPlus,btnWeightMinus){
            currentWeight = it
        }
        setupButtonHeight(btnHeightPlus,btnHeightMinus){
            currentHeight = it
        }
        setupButtonAge(btnAgePlus,btnAgeMinus){
            currentAge = it
        }
        binding.btnCalculate.setOnClickListener {
                calculateBMI()
        }

        weight.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    currentWeight = s.toString().toInt()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

// Set a TextWatcher for height EditText
        height.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    currentHeight = s.toString().toInt()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

// Set a TextWatcher for age EditText
        age.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!s.isNullOrBlank()) {
                    currentAge = s.toString().toInt()
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
    private fun calculateBMI() {
        if(currentHeight> 0 && currentWeight >0 && currentAge >0){
            val heightInMeters = currentHeight.toDouble() / 100.0
            val bmi = currentWeight / (heightInMeters * heightInMeters)
            val intent = Intent(this,ResultActivity::class.java)
            intent.putExtra("bmi",bmi.toFloat())
            intent.putExtra("age",currentAge.toString())
            Log.d("BMI",currentAge.toString())
            intent.putExtra("weight",currentWeight.toString())
            intent.putExtra("height",currentHeight.toString())
            startActivity(intent)
            Log.d("Main", "Sending BMI: $bmi")

        }else{
            Toast.makeText(this,"Please select",Toast.LENGTH_SHORT).show()
        }
    }

    // Replace your existing setupButtonAge function with this one
    private fun setupButtonWeight(btnPlus: ImageButton, btnMinus: ImageButton, valueUpdater: (Int) -> Unit) {
        btnPlus.setOnClickListener {
            currentWeight++
            valueUpdater.invoke(currentWeight)
            weight.text = Editable.Factory.getInstance().newEditable(currentWeight.toString())
        }
        btnMinus.setOnClickListener {
            Log.d("btnMinus", "Minus button visibility: ${btnMinus.visibility}")
            if (currentWeight > 0) {
                currentWeight--
                valueUpdater.invoke(currentWeight)
                weight.text = Editable.Factory.getInstance().newEditable(currentWeight.toString())
            }
        }
    }

    // Inside setupButtonHeight function
    private fun setupButtonHeight(btnHeightPlus: ImageButton, btnHeightMinus: ImageButton, valueUpdater: (Int) -> Unit) {
        btnHeightPlus.setOnClickListener {
            currentHeight++
            valueUpdater.invoke(currentHeight)
            height.text = Editable.Factory.getInstance().newEditable(currentHeight.toString())
        }
        btnHeightMinus.setOnClickListener {
            if (currentHeight > 0) {
                currentHeight--
                valueUpdater.invoke(currentHeight)
                height.text = Editable.Factory.getInstance().newEditable(currentHeight.toString())
            }
        }
    }

    // Inside setupButtonAge function
    private fun setupButtonAge(
        btnAgePlus: ImageButton,
        btnAgeMinus: ImageButton,
        function: (Int) -> Unit
    ) {
        btnAgePlus.setOnClickListener {
            currentAge++
            function.invoke(currentAge)
            age.text = Editable.Factory.getInstance().newEditable(currentAge.toString())
        }
        btnAgeMinus.setOnClickListener {
            if (currentAge > 0) {
                currentAge--
                function.invoke(currentAge)
                age.text = Editable.Factory.getInstance().newEditable(currentAge.toString())
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.date -> {
                return true
            }
            R.id.setting ->{
                return true
            }
            R.id.about ->{
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}