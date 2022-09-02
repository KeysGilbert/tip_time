package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener() {
            calculateTip()
        }
    }

    fun calculateTip() {
        val textfieldValue = binding.costOfService.text.toString()
        val cost = textfieldValue.toDouble()
        val selectedPercentage = binding.tipOptions.checkedRadioButtonId

        val tip = when(selectedPercentage) {
            R.id.twenty_percent -> 0.20
            R.id.eightteen_percent -> 0.18
            else -> 0.15
        }

        var result = cost * tip
        val roundUp = binding.roundSwitch.isChecked

        if(roundUp) {
            result = ceil(result)
        }
    }
}