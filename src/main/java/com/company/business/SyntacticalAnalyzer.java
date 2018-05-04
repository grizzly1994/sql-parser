package com.company.business;

import com.company.LexemeType;
import com.company.exception.ParserException;
import com.company.model.Lexeme;
import com.company.model.test.*;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Collections.singleton;

public class SyntacticalAnalyzer {

    private Collection<Position> positions;

    public SyntacticalAnalyzer() {
        Brunch star = new Brunch.Builder()
            .next(new SimpleNode(LexemeType.STAR))
            .build();
        Brunch tables = new Brunch.Builder()
            .next(new SimpleNode(LexemeType.IDENTIFIER))
            .build();
        Brunch select = new Brunch.Builder()
            .next(new SimpleNode(LexemeType.SELECT))
            .brunch(star)
            .brunch(tables)
            .build();
        Brunch from = new Brunch.Builder()
            .next(new SimpleNode(LexemeType.FROM))
            .next(new SimpleNode(LexemeType.IDENTIFIER))
            .build();
        Brunch root = new Brunch.Builder()
            .next(new NestedNode(select))
            .next(new NestedNode(from))
            .build();
        positions = singleton(new Position(root, new Result()));
    }

    public void parse(Lexeme lexeme) {
        Collection<Position> next = new ArrayList<>();
        for (Position position : positions) {
            Collection<Position> positions = position.next();
            positions = parse(positions);
            next.addAll(positions);
        }
        positions = next;
    }

    private Collection<Position> parse(Collection<Position> positions) {
        Collection<Position> next = new ArrayList<>();
        for (Position position : positions) {
            next.addAll(position.getNode().visit(new NodeVisitor<Collection<Position>>() {
                @Override
                public Collection<Position> visit(SimpleNode node) {
                    return singleton(position);
                }

                @Override
                public Collection<Position> visit(NestedNode node) {
                    return new Position(node.getBrunch(), position.getResult(), position).next();
                }
            }));
        }
        return next;
    }

    public void end() throws ParserException {
        for (Position position : positions) {
            if (position.isComplete() && position.isTerminal()) {
                return;
            }
        }
        throw new ParserException();
    }
}
