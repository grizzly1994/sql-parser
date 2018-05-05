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
        Brunch multipleTables = new Brunch.Builder()
            .next(new SimpleNode(LexemeType.COMMA))
            .next(new SimpleNode(LexemeType.IDENTIFIER))
            .build();
        Brunch tables = new Brunch.Builder()
            .next(new SimpleNode(LexemeType.IDENTIFIER))
            .next(new NestedNode(multipleTables, Node.Flag.OPTIONAL))
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
        positions = singleton(Position.create(root, new Result()));
    }

    public void parse(Lexeme lexeme) {
        Collection<Position> next = new ArrayList<>();
        for (Position position : positions) {
            Collection<Position> positions = position.getNext();
            positions = parse(positions, lexeme);
            next.addAll(positions);
        }
        positions = next;
    }

    private Collection<Position> parse(Collection<Position> positions, Lexeme lexeme) {
        Collection<Position> simple = new ArrayList<>();
        Collection<Position> nested = positions;
        do {
            Collection<Position> result = new ArrayList<>();
            for (Position position : nested) {
                if (position.getNode().getFlags().contains(Node.Flag.OPTIONAL)) {
                    result.addAll(position.getNext());
                }
                position.getNode().visit(new NodeVisitor() {
                    @Override
                    public void visit(SimpleNode node) {
                        if (ObjectUtils.equals(lexeme.getType(), node.getLexemeType())) {
                            simple.add(position);
                        }
                    }

                    @Override
                    public void visit(NestedNode node) {
                        result.addAll(Position.create(node.getBrunch(), position.getResult(), position).getNext());
                    }
                });
            }
            nested = result;
        } while (!nested.isEmpty());
        return simple;
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
