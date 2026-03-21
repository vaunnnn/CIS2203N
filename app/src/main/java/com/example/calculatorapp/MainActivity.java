package com.example.calculatorapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.calculatorapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private double runningTotal = 0;
    private String currentOperator = "";
    private boolean isFirstInput = true;
    private boolean isEqualsPressed = false;
    private final String lastThreeDigitsOfIDNumber = "4.36";

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble("totalKey", runningTotal);
        outState.putString("operatorKey", currentOperator);
        outState.putBoolean("firstInputKey", isFirstInput);
        outState.putBoolean("equalPressedKey", isEqualsPressed);
        outState.putString("resultText", getCurrentResult());
        outState.putString("inputText", getCurrentInput());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(savedInstanceState != null) {
            runningTotal = savedInstanceState.getDouble("totalKey");
            currentOperator = savedInstanceState.getString("operatorKey");
            isFirstInput = savedInstanceState.getBoolean("firstInputKey");
            isEqualsPressed = savedInstanceState.getBoolean("equalPressedKey");
            setResultText(savedInstanceState.getString("resultText"));
            setInputText(savedInstanceState.getString("inputText"));
        }

        //Function to handle the number inputs
        View.OnClickListener numberClickListener = view -> {
            Button clickedBtn = (Button) view;
            String numberStr = clickedBtn.getText().toString();

            appendNumberToInput(numberStr);
        };

        //Function to handle decimal numbers;
        View.OnClickListener decimalClickListener = view -> {
            if(!isDecimalExist()) {
                appendNumberToInput(".");
            }
        };

        //Function to handle magic number
        View.OnClickListener magicClickListener = view -> {
            appendNumberToInput(lastThreeDigitsOfIDNumber);
        };

        //Function to handle the reset
        View.OnClickListener resetClickListener = view -> {
            resetInput();
            resetResult();
            resetCalc();
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

            if(!currentNumber.isEmpty() && !isCurrentInputZero()) {
                String updatedNumber = removeNumberFromInput(currentNumber);
                setInputText(updatedNumber);
            }
        };

        View.OnClickListener equalsClickListener = view -> {
            String inputStr = getCurrentInput();

            if(isDivisionByZero(inputStr)) {
                setInputText("DNE");
                return;
            } else if (isEqualsPressedWithoutAnInput(inputStr) || isUndefined(inputStr)) {
                resetInput();
                resetResult();
                resetCalc();
                return;
            }

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

        //Decimal event listener
        binding.dotBtn.setOnClickListener(decimalClickListener);

        //Magic event listener
        binding.magic.setOnClickListener(magicClickListener);

        //Reset event listener
        binding.resetButton.setOnClickListener(resetClickListener);

        //Operand event listener
        binding.addBtn.setOnClickListener(operandClickListener);
        binding.subtractBtn.setOnClickListener(operandClickListener);
        binding.multiplyBtn.setOnClickListener(operandClickListener);
        binding.divideBtn.setOnClickListener(operandClickListener);
        binding.moduloBtn.setOnClickListener(operandClickListener);

        //Remove Number event listener
        binding.removeBtn.setOnClickListener(removeClickListener);

        //Equal event listener
        binding.equalBtn.setOnClickListener(equalsClickListener);

    }

    private void appendNumberToInput(String number) {

        String currentText = getCurrentInput();
        if(isCurrentInputZero()) {
            if (number.equals(".")) {
                setInputText("0.");
            } else {
                setInputText(number);
            }
            return;
        }
        String newText = currentText + number;
        setInputText(newText);
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
            case "%": runningTotal %= newNumber; break;
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

    private void resetCalc() {
        runningTotal = 0;
        currentOperator = "";
        isFirstInput = true;
        isEqualsPressed = false;
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

    private boolean isDivisionByZero(String inputStr) {
        return isCurrentInputZero() && currentOperator.equals("/");
    }

    private  boolean isEqualsPressedWithoutAnInput(String inputStr) {
        return currentOperator.isEmpty();
    }

    private boolean isUndefined(String inputStr) {
        return inputStr.equals("DNE");
    }

    private boolean isDecimalExist() {
        String currentInput = getCurrentInput();

        return currentInput.contains(".");
    }

    private boolean isCurrentInputZero() {
        String currentInput = getCurrentInput();
        return currentInput.equals("0");
    }

    @SuppressLint("DefaultLocale")
    private String formatNumber(double d) {
        if (d == (long) d)
            return String.format("%d", (long) d);
        else
            return String.format("%.2f", d);
    }
}
