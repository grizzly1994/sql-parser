package com.company.model.test;

public class Stack<TValue> {

    private final TValue value;
    private final Stack<TValue> parent;

    public Stack(TValue value, Stack<TValue> parent) {
        this.value = value;
        this.parent = parent;
    }

    public TValue getValue() {
        return value;
    }

    public Stack<TValue> getParent() {
        return parent;
    }
}
