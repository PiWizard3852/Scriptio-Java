package org.scriptio.parser;

public class Token {
    public TokenTypes type;
    public String value;

    Token(TokenTypes type, String value) {
        this.type = type;
        this.value = value;
    }

    public static Token.TokenTypes getKeywordTokenType(String value) {
        return switch (value) {
            //    make
            case "fac" -> TokenTypes.VariableDeclaration;
            //    number,    string,   boolean
            case "numerus", "verbum", "veredictumne" -> TokenTypes.Type;
            //    mutable
            case "variabilis" -> TokenTypes.Mutable;
            //    null
            case "nihil" -> TokenTypes.Null;
            //    true     false
            case "verus", "falsus" -> TokenTypes.Boolean;
            case "+", "-", "*", "/", "%" -> TokenTypes.BinaryOperator;
            case "++", "--" -> TokenTypes.UpdateOperator;
            case "=", "+=", "-=", "*=", "/=", "%=" ->
                TokenTypes.AssignmentOperator;
            default -> null;
        };
    }

    public enum TokenTypes {
        VariableDeclaration,
        Identifier,
        Mutable,
        Type,
        UpdateOperator,
        AssignmentOperator,
        Null,
        Number,
        String,
        Boolean,
        OpenParen,
        CloseParen,
        BinaryOperator,
        SemiColon
    }
}
