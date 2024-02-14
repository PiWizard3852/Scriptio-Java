package org.scriptio.lexer;

public class Token {
    public TokenTypes type;
    public String value;

    Token(TokenTypes type, String value) {
        this.type = type;
        this.value = value;
    }

    public enum TokenTypes {
        Declaration,
        Function,
        Identifier,
        Type,
        Equals,
        Number,
        OpenParen,
        CloseParen,
        BinaryOperator;
    }
}
