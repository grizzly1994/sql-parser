package com.company.model.test;

public interface NodeVisitor {
    void visit(SimpleNode node);
    void visit(NestedNode node);
}
