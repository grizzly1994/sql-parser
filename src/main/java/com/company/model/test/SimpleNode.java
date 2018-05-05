package com.company.model.test;

import com.company.LexemeType;

import java.util.Collection;
import java.util.HashSet;

import static java.util.Arrays.asList;

public class SimpleNode implements Node {

    private final LexemeType lexemeType;
    private final Reducer reducer;
    private final Collection<Flag> flags;

    public SimpleNode(LexemeType lexemeType, Reducer reducer, Flag... flags) {
        this.lexemeType = lexemeType;
        this.reducer = reducer;
        this.flags = new HashSet<>(asList(flags));
    }

    public SimpleNode(LexemeType lexemeType, Flag... flags) {
        this(lexemeType, result -> result, flags);
    }

    public LexemeType getLexemeType() {
        return lexemeType;
    }

    public Result reducer(Result result) {
        return reducer.reduce(result);
    }

    @Override
    public void visit(NodeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Collection<Flag> getFlags() {
        return flags;
    }

    public interface Reducer {
        Result reduce(Result result);
    }
}
