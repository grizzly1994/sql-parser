package com.company.model.test;

public interface Node {
    <TResult> TResult visit(NodeVisitor<TResult> visitor);
}
