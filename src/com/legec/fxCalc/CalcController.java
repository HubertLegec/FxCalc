package com.legec.fxCalc;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static com.legec.fxCalc.State.*;
import static java.lang.Double.parseDouble;

class CalcController {
    private StringProperty displayState = new SimpleStringProperty("0");
    private double value = 0;
    private State state = UNKNOWN;

    void processAction(String action) {
        String displayVal = displayState.get();
        switch (action) {
            case "C":
                displayState.set("0");
                value = 0;
                state = UNKNOWN;
                break;
            case "sqrt":
                sqrtAction();
                break;
            case "%":
                percentAction();
                break;
            case "/":
                divAction(displayVal);
                break;
            case "*":
                mulAction(displayVal);
                break;
            case "-":
                subAction(displayVal);
                break;
            case "+":
                addAction(displayVal);
                break;
            case "+/-":
                signAction(displayVal);
                break;
            case "=":
                resultAction();
                break;
            case ".":
                dotAction();
                break;
            default:
                numberAction(action);
        }
    }

    StringProperty getDisplayStateProperty() {
        return displayState;
    }

    private void addAction(String displayVal) {
        value += parseDouble(displayVal);
        state = SUM;
        displayState.set("0");
    }

    private void subAction(String displayVal) {
        double parsedVal = parseDouble(displayVal);
        value = value == 0 ? parsedVal : value - parsedVal;
        state = SUB;
        displayState.set("0");
    }

    private void mulAction(String displayVal) {
        double parsedVal = parseDouble(displayVal);
        value = value == 0 ? parsedVal : value * parsedVal;
        state = MUL;
        displayState.set("0");
    }

    private void divAction(String displayVal) {
        double parsedVal = parseDouble(displayVal);
        value = value == 0 ? parsedVal : value / parsedVal;
        state = DIV;
        displayState.set("0");
    }

    private void numberAction(String val) {
        if (state != ERROR) {
            addToDisplay(val);
        }
    }

    private void dotAction() {
        if (state != UNKNOWN && !displayState.get().contains(".")) {
            addToDisplay(".");
            value = parseDouble(displayState.get());
        } else {
            state = ERROR;
            displayState.set("ERR");
        }
    }

    private void sqrtAction() {
        if (state == UNKNOWN) {
            value = Math.sqrt(value);
            if (Double.isNaN(value)) {
                displayState.set("ERR");
                state = ERROR;
            } else {
                displayState.set(Double.toString(value));
                state = UNKNOWN;
            }
        } else {
            state = ERROR;
            displayState.set("ERR");
        }
    }

    private void percentAction() {

    }

    private void signAction(String displayVal) {
        if (displayVal.isEmpty() || "0".equals(displayVal)) {
            return;
        }
        if (displayVal.startsWith("-")) {
            displayState.set(displayVal.substring(1));
        } else {
            displayState.set("-" + displayVal);
        }
        if (state == UNKNOWN) {
            value = parseDouble(displayState.get());
        }
    }

    private void resultAction() {
        double secondComponent = parseDouble(displayState.get());
        switch (state) {
            case SUM:
                value += secondComponent;
                break;
            case SUB:
                value -= secondComponent;
                break;
            case DIV:
                value /= secondComponent;
                break;
            case MUL:
                value *= secondComponent;
                break;

        }
        displayState.set(Double.toString(value));
        value = 0;
        state = UNKNOWN;
    }

    private void addToDisplay(String val) {
        String displayVal = displayState.get();
        displayState.set("0".equals(displayVal) ? val : displayVal + val);
    }

}
