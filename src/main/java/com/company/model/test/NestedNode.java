package com.company.model.test;

public class NestedNode implements Node {

    private final Brunch brunch;

    public NestedNode(Brunch brunch) {
        this.brunch = brunch;
    }

    @Override
    public <TResult> TResult visit(NodeVisitor<TResult> visitor) {
        return visitor.visit(this);
    }

    public Brunch getBrunch() {
        return brunch;
    }
}
