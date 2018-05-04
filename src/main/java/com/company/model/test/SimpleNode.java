package com.company.model.test;

import com.company.LexemeType;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableSet;

public class SimpleNode implements Node {

    private final LexemeType lexemeType;
    private final Reducer reducer;
    private final Set<Setting> settings;

    public SimpleNode(LexemeType lexemeType, Reducer reducer, Setting... settings) {
        this.lexemeType = lexemeType;
        this.reducer = reducer;
        this.settings = new HashSet<>(asList(settings));
    }

    public SimpleNode(LexemeType lexemeType, Setting... settings) {
        this(lexemeType, result -> result, settings);
    }

    public LexemeType getLexemeType() {
        return lexemeType;
    }

    public boolean isRequired() {
        return settings.contains(Setting.REQUIRED);
    }

    public boolean isRepeatable() {
        return settings.contains(Setting.REPEATABLE);
    }

    public Result reducer(Result result) {
        return reducer.reduce(result);
    }

    @Override
    public <TResult> TResult visit(NodeVisitor<TResult> visitor) {
        return visitor.visit(this);
    }

    public interface Reducer {
        Result reduce(Result result);
    }

    public enum Setting {
        REQUIRED,
        REPEATABLE
    }
}
