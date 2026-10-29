
package com.basiccalculator;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private TextField display;

    private double firstNumber = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    @FXML
    private void handleButtonClick(ActionEvent event) {

        Button button = (Button) event.getSource();
        String value = button.getText();

        // Number buttons and decimal point
        if (value.matches("[0-9]") || value.equals(".")) {

            if (startNewNumber) {
                if (value.equals(".")) {
                    display.setText("0.");
                } else {
                    display.setText(value);
                }

                startNewNumber = false;

            } else if (value.equals(".")) {

                if (!display.getText().contains(".")) {
                    display.setText(display.getText() + ".");
                }

            } else {
                display.setText(display.getText() + value);
            }
        }

        // Operator buttons
        else if (value.equals("+") ||
                 value.equals("-") ||
                 value.equals("*") ||
                 value.equals("/")) {

            firstNumber = Double.parseDouble(display.getText());
            operator = value;
            startNewNumber = true;
        }

        // Equals button
        else if (value.equals("=")) {

            if (operator.isEmpty()) {
                return;
            }

            double secondNumber =
                    Double.parseDouble(display.getText());

            double result = 0;

            switch (operator) {
                case "+":
                    result = firstNumber + secondNumber;
                    break;

                case "-":
                    result = firstNumber - secondNumber;
                    break;

                case "*":
                    result = firstNumber * secondNumber;
                    break;

                case "/":
                    if (secondNumber == 0) {
                        display.setText("Cannot divide by zero");
                        operator = "";
                        startNewNumber = true;
                        return;
                    }

                    result = firstNumber / secondNumber;
                    break;
            }

            display.setText(String.valueOf(result));
            operator = "";
            startNewNumber = true;
        }

        // Clear button
        else if (value.equals("C")) {

            display.setText("0");
            firstNumber = 0;
            operator = "";
            startNewNumber = true;
        }
    }
}
