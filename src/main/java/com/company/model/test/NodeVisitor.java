package com.company.model.test;

public interface NodeVisitor<TResult> {
    TResult visit(SimpleNode node);
    TResult visit(NestedNode node);
}
