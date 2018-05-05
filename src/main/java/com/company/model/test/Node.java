package com.company.model.test;

import java.util.Collection;

public interface Node {
    void visit(NodeVisitor visitor);
    Collection<Flag> getFlags();

    enum Flag {
        OPTIONAL
    }
}
