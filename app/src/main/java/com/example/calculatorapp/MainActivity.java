package com.example.calculatorapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import com.example.calculatorapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private double runningTotal = 0;
    private String currentOperator = "";
    private boolean isFirstInput = true;
    private boolean isEqualsPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Function to handle the number inputs
        View.OnClickListener numberClickListener = view -> {
            Button clickedBtn = (Button) view;
            String numberStr = clickedBtn.getText().toString();

            appendNumberToInput(numberStr);
        };

        //Function to handle the reset
        View.OnClickListener resetClickListener = view -> {
            resetInput();
            resetResult();
            runningTotal = 0;
            currentOperator = "";
            isFirstInput = true;
        };

        //Function to handle the operands and output the current input + the operand in the "result"
        View.OnClickListener operandClickListener = view -> {
            Button clickedOperand = (Button) view;
            String nextOperatorClicked = clickedOperand.getText().toString();

            handleOperator(nextOperatorClicked);
        };

        //Function to handle removing of numbers in input
        View.OnClickListener removeClickListener = view -> {
            String currentNumber = getCurrentInput();

            if(!currentNumber.isEmpty() && !currentNumber.equals("0")) {
                String updatedNumber = removeNumberFromInput(currentNumber);
                setInputText(updatedNumber);
            }
        };

        View.OnClickListener equalsClickListener = view -> {
            String inputStr = getCurrentInput();
            finalizeCalculation(inputStr);
            isEqualsPressed = true;
        };


        //Numbers event listener
        binding.num0.setOnClickListener(numberClickListener);
        binding.num1.setOnClickListener(numberClickListener);
        binding.num2.setOnClickListener(numberClickListener);
        binding.num3.setOnClickListener(numberClickListener);
        binding.num4.setOnClickListener(numberClickListener);
        binding.num5.setOnClickListener(numberClickListener);
        binding.num6.setOnClickListener(numberClickListener);
        binding.num7.setOnClickListener(numberClickListener);
        binding.num8.setOnClickListener(numberClickListener);
        binding.num9.setOnClickListener(numberClickListener);

        //Reset event listener
        binding.resetButton.setOnClickListener(resetClickListener);

        //Operand event listener
        binding.addBtn.setOnClickListener(operandClickListener);
        binding.subtractBtn.setOnClickListener(operandClickListener);
        binding.multiplyBtn.setOnClickListener(operandClickListener);
        binding.divideBtn.setOnClickListener(operandClickListener);

        //Remove Number event listener
        binding.removeBtn.setOnClickListener(removeClickListener);

        //Equal event listener
        binding.equalBtn.setOnClickListener(equalsClickListener);
    }


    private void appendNumberToInput(String number) {
        String currentText = binding.input.getText().toString();

        if(currentText.equals("0")) {
            String firstText = number;
            setInputText(firstText);
        } else {
            String newText = currentText + number;
            setInputText(newText);
        }
    }

    private void handleOperator(String nextOperator) {

        String inputStr = getCurrentInput();
        double inputNum = Double.parseDouble(inputStr);

        if(isEqualsPressed) {
            isEqualsPressed = false;
        } else if(isFirstInput) {
            runningTotal = inputNum;
            isFirstInput = false;
        } else {
            performCalculation(inputNum);
        }

        currentOperator = nextOperator;

        String resultString = formatNumber(runningTotal) + " " + currentOperator;
        setResultText(resultString);

        resetInput();
    }

    private void finalizeCalculation(String inputStr) {
        double inputNum = Double.parseDouble(inputStr);

        String fullStory = formatNumber(runningTotal) + " " + currentOperator + " " + formatNumber(inputNum) + " =";

        performCalculation(inputNum);

        setResultText(fullStory);
        setInputText(formatNumber(runningTotal));
    }
    private void performCalculation(Double newNumber) {
        switch(currentOperator) {
            case "+": runningTotal += newNumber; break;
            case "-": runningTotal -= newNumber; break;
            case "*": runningTotal *= newNumber; break;
            case "/":
                if (newNumber != 0) runningTotal /= newNumber;
                break;
        }
    }

    private String removeNumberFromInput(String number) {
        if(number.length() <= 1) {
            return "0";
        }

        return number.substring(0, number.length() - 1);
    }


    private String getCurrentResult() {
        if(binding.result.getText().toString().equals("0")) {
            return "";
        }

        return binding.result.getText().toString();
    }

    private String getCurrentInput() {
        return binding.input.getText().toString();
    }
    private void resetInput() {
        binding.input.setText("0");
    }

    private void resetResult() {
        binding.result.setText("0");
    }

    private void setInputText(String number) {
        binding.input.setText(number);
    }

    private void setResultText(String number) {
        binding.result.setText(number);
    }


    @SuppressLint("DefaultLocale")
    private String formatNumber(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%s", d);
    }
}