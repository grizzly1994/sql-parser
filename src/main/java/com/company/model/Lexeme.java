package com.company.model;

import com.company.LexemeType;

public class Lexeme {

    private final int position;
    private final LexemeType lexemeType;
    private final String text;

    public Lexeme(int position, LexemeType lexemeType, String text) {
        this.position = position;
        this.lexemeType = lexemeType;
        this.text = text;
    }

    public int getPosition() {
        return position;
    }

    public LexemeType getType() {
        return lexemeType;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Lexeme { position: '" + position + "', type: '" + lexemeType + "', text: '" + text + "' }";
    }
}
