package com.example.exercise4

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    var currentInput = ""
    var firstOperand = 0.0
    var currentOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val tvDisplay = findViewById<TextView>(R.id.tv_display)

        val btn1 = findViewById<Button>(R.id.btn_1)
        val btn2 = findViewById<Button>(R.id.btn_2)
        val btn3 = findViewById<Button>(R.id.btn_3)
        val btn4 = findViewById<Button>(R.id.btn_4)
        val btn5 = findViewById<Button>(R.id.btn_5)
        val btn6 = findViewById<Button>(R.id.btn_6)
        val btn7 = findViewById<Button>(R.id.btn_7)
        val btn8 = findViewById<Button>(R.id.btn_8)
        val btn9 = findViewById<Button>(R.id.btn_9)
        val btn0 = findViewById<Button>(R.id.btn_0)

        val btnAdd = findViewById<Button>(R.id.btn_add)
        val btnSubtract = findViewById<Button>(R.id.btn_subtract)
        val btnMultiply = findViewById<Button>(R.id.btn_multiply)
        val btnDivide = findViewById<Button>(R.id.btn_divide)

        val btnEquals = findViewById<Button>(R.id.btn_equals)
        val btnClear = findViewById<Button>(R.id.btn_clear)

        btn1.setOnClickListener {
            currentInput += "1"
            tvDisplay.text = currentInput
        }
        btn2.setOnClickListener {
            currentInput += "2"
            tvDisplay.text = currentInput
        }

        btn3.setOnClickListener {
            currentInput += "2"
            tvDisplay.text = currentInput
        }

        btn4.setOnClickListener {
            currentInput += "2"
            tvDisplay.text = currentInput
        }

        btn5.setOnClickListener {
            currentInput += "2"
            tvDisplay.text = currentInput
        }

        btn7.setOnClickListener {
            currentInput += "2"
            tvDisplay.text = currentInput
        }

        btn8.setOnClickListener {
            currentInput += "2"
            tvDisplay.text = currentInput
        }

        btn9.setOnClickListener {
            currentInput += "2"
            tvDisplay.text = currentInput
        }

        btn0.setOnClickListener {
            currentInput += "2"
            tvDisplay.text = currentInput
        }

        btn2.setOnClickListener {
            currentInput += "2"
            tvDisplay.text = currentInput
        }

        btnAdd.setOnClickListener {
            firstOperand = currentInput.toDoubleOrNull() ?: 0.0
            currentOperator = "+"
            currentInput = ""
        }

        btnSubtract.setOnClickListener {
            firstOperand = currentInput.toDoubleOrNull() ?: 0.0
            currentOperator = "-"
            currentInput = ""
        }

        btnMultiply.setOnClickListener {
            firstOperand = currentInput.toDoubleOrNull() ?: 0.0
            currentOperator = "*"
            currentInput = ""
        }

        btnDivide.setOnClickListener {
            firstOperand = currentInput.toDoubleOrNull() ?: 0.0
            currentOperator = "/"
            currentInput = ""
        }
        btnEquals.setOnClickListener {
            val secondOperand = currentInput.toDoubleOrNull() ?: 0.0
            val result = performMath(firstOperand, secondOperand, currentOperator)

            tvDisplay.text = result.toString()

            currentInput = result.toString()
            firstOperand = result
            currentOperator = ""
        }

        btnClear.setOnClickListener {
            currentInput = ""
            firstOperand = 0.0
            currentOperator = ""
            tvDisplay.text = "0"
        }
    }

    fun performMath(operand1: Double, operand2: Double, operator: String): Double {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> if (operand2 != 0.0) operand1 / operand2 else 0.0
            else -> 0.0
        }
    }
}