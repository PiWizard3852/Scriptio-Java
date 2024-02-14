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
        String,
        Boolean,
        Quote,
        OpenParen,
        CloseParen,
        OpenBrace,
        CloseBrace,
        BinaryOperator;
    }

    public static Token.TokenTypes getKeywordTokenType(String value) {
        return switch (value) {
            case "fac" -> Token.TokenTypes.Declaration;
            case "numerus", "verbum", "veredictumne" -> Token.TokenTypes.Type;
            case "verus", "falsus" -> TokenTypes.Boolean;
            case "factum" -> Token.TokenTypes.Function;
            default -> null;
        };
    }
}
