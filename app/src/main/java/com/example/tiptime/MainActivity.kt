package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.HIDE_IMPLICIT_ONLY
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
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

        binding.costOfServiceEditText.setOnKeyListener() {
            view, keycode, _ -> handleKeyEvent(view, keycode)
        }
    }

    //hide enter key
    private fun handleKeyEvent(view: View, keycode: Int): Boolean {
        if(keycode == KeyEvent.KEYCODE_ENTER) {
            val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }

        return false
    }

    fun calculateTip() {

        //get input value
        val textfieldValue = binding.costOfServiceEditText.text.toString()
        val cost = textfieldValue.toDouble()
        val selectedPercentage = binding.tipOptions.checkedRadioButtonId

        //store tip percentage based on radio button checked
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

        //display tip
        val formattedResult = NumberFormat.getCurrencyInstance().format(result)
        binding.tipAmount.text = getString(R.string.tip_amount, formattedResult)
    }
}