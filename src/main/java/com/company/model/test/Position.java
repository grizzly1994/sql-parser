package com.company.model.test;

import java.util.Collection;

import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toList;

public class Position {

    public static Position create(Brunch brunch, Result result, Position parent) {
        return new Position(brunch, result, START, parent);
    }

    public static Position create(Brunch brunch, Result result) {
        return new Position(brunch, result);
    }

    private static final int START = -1;

    private final Brunch brunch;
    private final Position parent;
    private final int index;
    private final Result result;

    private Position(Brunch brunch, Result result, int index, Position parent) {
        this.brunch = brunch;
        this.result = result;
        this.index = index;
        this.parent = parent;
    }

    private Position(Brunch brunch, Result result) {
        this(brunch, result, START, null);
    }

    private Position(Brunch brunch, Result result, Position parent) {
        this(brunch, result, START, parent);
    }

    public Collection<Position> getNext() {
        if (isComplete()) {
            if (isTerminal() && parent != null) {
                return parent.getNext();
            }
            return brunch.getBrunches().stream()
                .flatMap(brunch -> new Position(brunch, result, parent).getNext().stream())
                .collect(toList());
        } else {
            return singleton(new Position(brunch, result, index + 1, parent));
        }
    }

    public Node getNode() {
        return brunch.getNodes().get(index);
    }

    public Result getResult() {
        return result;
    }

    public boolean isComplete() {
        return index >= brunch.getNodes().size() - 1;
    }

    public boolean isTerminal() {
        return brunch.getBrunches().isEmpty();
    }
}
