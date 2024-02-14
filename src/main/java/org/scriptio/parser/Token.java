package org.scriptio.parser;

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
        Null,
        Number,
        String,
        Boolean,
        Quote,
        OpenParen,
        CloseParen,
        OpenBrace,
        CloseBrace,
        BinaryOperator,
        SemiColon;
    }

    public static Token.TokenTypes getKeywordTokenType(String value) {
        return switch (value) {
            case "fac" -> TokenTypes.Declaration;
            case "numerus", "verbum", "veredictumne" -> TokenTypes.Type;
            case "nihil" -> TokenTypes.Null;
            case "verus", "falsus" -> TokenTypes.Boolean;
            case "factum" -> Token.TokenTypes.Function;
            default -> null;
        };
    }
}
