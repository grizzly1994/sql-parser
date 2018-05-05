package com.company.model.test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class NestedNode implements Node {

    private final Brunch brunch;
    private final Collection<Flag> flags;

    public NestedNode(Brunch brunch, Flag... flags) {
        this.brunch = brunch;
        this.flags = new HashSet<>(asList(flags));
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }

    public Brunch getBrunch() {
        return brunch;
    }

    public Collection<Flag> getFlags() {
        return flags;
    }
}
