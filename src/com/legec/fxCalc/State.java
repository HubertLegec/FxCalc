package com.legec.fxCalc;


enum State {
    UNKNOWN((a, b) -> b),
    SUM((a, b) -> a + b),
    SUB((a, b) -> a - b),
    MUL((a, b) -> a * b),
    DIV((a, b) -> a / b),
    ERROR(null);

    private final BiOperator operator;

    State(BiOperator operator) {
        this.operator = operator;
    }

    public BiOperator getOperator() {
        return operator;
    }
}
