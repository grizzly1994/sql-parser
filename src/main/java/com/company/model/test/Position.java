package com.company.model.test;

import java.util.Collection;

import static java.util.Collections.emptyList;
import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toList;

public class Position {

    private final Brunch brunch;
    private final Position parent;

    private int position = -1;
    private Result result;

    public Position(Brunch brunch, Result result, Position parent) {
        this.brunch = brunch;
        this.result = result;
        this.parent = parent;
    }

    public Position(Brunch brunch, Result result) {
        this(brunch, result, null);
    }

    public Collection<Position> next() {
        if (isComplete()) {
            if (isTerminal() && parent != null) {
                return parent.next();
            }
            return brunch.getBrunches().stream()
                .flatMap(brunch -> new Position(brunch, Result.copy(result), parent).next().stream())
                .collect(toList());
        } else {
            position++;
            return singleton(this);
        }
    }

    public Node getNode() {
        return brunch.getNodes().get(position);
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public boolean isComplete() {
        return position == brunch.getNodes().size() - 1;
    }

    public boolean isTerminal() {
        return brunch.getBrunches().isEmpty();
    }
}
