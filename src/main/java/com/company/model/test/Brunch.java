package com.company.model.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Brunch {

    private final List<Node> nodes;
    private final Collection<Brunch> brunches;

    private Brunch(Builder builder) {
        this.nodes = new ArrayList<>(builder.nodes);
        this.brunches = new ArrayList<>(builder.brunches);
    }

    public List<Node> getNodes() {
        return new ArrayList<>(nodes);
    }

    public Collection<Brunch> getBrunches() {
        return new ArrayList<>(brunches);
    }

    public static class Builder {

        private final List<Node> nodes = new ArrayList<>();
        private final Collection<Brunch> brunches = new ArrayList<>();

        public Builder next(Node node) {
            nodes.add(node);
            return this;
        }

        public Builder brunch(Brunch brunch) {
            brunches.add(brunch);
            return this;
        }

        public Brunch build() {
            return new Brunch(this);
        }
    }
}
