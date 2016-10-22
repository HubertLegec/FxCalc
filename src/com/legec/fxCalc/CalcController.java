package com.legec.fxCalc;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import static com.legec.fxCalc.State.ERROR;
import static com.legec.fxCalc.State.UNKNOWN;

class CalcController {
    private StringProperty displayState = new SimpleStringProperty("0");
    private double value = 0;
    private State state = UNKNOWN;

    void processAction(String action) {
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

                break;
            case "*":

                break;
            case "-":

                break;
            case "+":

                break;
            case "+/-":

                break;
            case "=":

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


    private void numberAction(String val) {
        if (state == UNKNOWN) {
            addToDisplay(val);
            value = Double.parseDouble(displayState.getValue());
        }
    }

    private void dotAction() {
        if (state == UNKNOWN && !displayState.get().contains(".")) {
            addToDisplay(".");
            value = Double.parseDouble(displayState.get());
        } else {
            state = ERROR;
            displayState.set("ERR");
        }
    }

    private void sqrtAction() {
        if (state == UNKNOWN) {
            double result = Math.sqrt(value);
            value = result;
            displayState.set(Double.toString(value));
            state = UNKNOWN;
        } else {
            state = ERROR;
            displayState.set("ERR");
        }
    }

    private void percentAction() {
        //TODO
    }

    private void addToDisplay(String val) {
        if ("0".equals(displayState.getValue())) {
            displayState.set(val);
        } else {
            displayState.set(displayState.getValue() + val);
        }
    }

}
