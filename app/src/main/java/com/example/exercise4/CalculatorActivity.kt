package com.example.exercise4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculatorActivity : AppCompatActivity() {

    var currentInput = ""
    var firstOperand = 0.0
    var currentOperator = ""
    val number = currentInput.toDoubleOrNull() ?: 0.0

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

    }

    fun addNumbers(a: Double, b: Double): Double {
        return a + b
    }


}