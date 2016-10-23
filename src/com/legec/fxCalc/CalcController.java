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
                sqrtAction(displayVal);
                break;
            case "%":
                resultAction(displayVal, true);
                break;
            case "/":
                biOperatorAction(DIV, displayVal);
                break;
            case "*":
                biOperatorAction(MUL, displayVal);
                break;
            case "-":
                biOperatorAction(SUB, displayVal);
                break;
            case "+":
                biOperatorAction(SUM, displayVal);
                break;
            case "+/-":
                signAction(displayVal);
                break;
            case "=":
                resultAction(displayVal, false);
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

    private void biOperatorAction(State state, String displayVal) {
        double parsedVal = parseDouble(displayVal);
        BiOperator operator = this.state.getOperator();
        value = value == 0 ? parsedVal :  operator.calculate(value, parsedVal);
        this.state = state;
        displayState.set("0");
    }

    private void numberAction(String val) {
        if (state != ERROR) {
            addToDisplay(val);
        }
    }

    private void dotAction() {
        if (state != ERROR && !displayState.get().contains(".")) {
            addToDisplay(".");
        } else {
            state = ERROR;
            displayState.set("ERR");
        }
    }

    private void sqrtAction(String displayVal) {
        double parsedVal = parseDouble(displayVal);
        if (state == UNKNOWN) {
            value = Math.sqrt(parsedVal);
            if (Double.isFinite(value)) {
                setResult();
                value = 0;
                state = UNKNOWN;
                return;
            }
        }
        state = ERROR;
        displayState.set("ERR");
    }

    private void signAction(String displayVal) {
        if (displayVal.isEmpty() || "0".equals(displayVal)) {
            return;
        }
        displayState.set(
                displayVal.startsWith("-") ?
                        displayVal.substring(1) :
                        "-" + displayVal
        );
    }

    private void resultAction(String displayVal, boolean percent) {
        double secondComponent = percent ?
                parseDouble(displayVal) / 100 :
                parseDouble(displayVal);
        BiOperator operator = state.getOperator();
        value = operator.calculate(value, secondComponent);
        setResult();
        value = 0;
        state = UNKNOWN;
    }

    private void addToDisplay(String val) {
        String displayVal = displayState.get();
        displayState.set(
                "0".equals(displayVal) && !".".equals(val) ?
                        val :
                        displayVal + val
        );
    }

    private void setResult() {
        int intVal = (int) value;
        displayState.set(
                intVal == value ?
                        Integer.toString(intVal) :
                        Double.toString(value)
        );
    }

}
