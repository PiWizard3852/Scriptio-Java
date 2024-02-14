package org.scriptio.lexer;

import java.util.ArrayList;
import java.util.Objects;

public class Lexer {
    String source;

    public Lexer(String source) {
        this.source = source;
    }

    private static boolean isWhitespace(char character) { return Character.isWhitespace(character); }

    private static boolean isAlphabetic(char character) { return Character.isAlphabetic(character); }

    private static boolean isDigit(char character) {
        return Character.isDigit(character);
    }


    private static Token.TokenTypes getKeywordTokenType(String value) {
        return switch (value) {
            case "fac" -> Token.TokenTypes.Declaration;
            case "numerus" -> Token.TokenTypes.Type;
            case "factum" -> Token.TokenTypes.Function;
            default -> null;
        };
    }

    public ArrayList<Token> lex() throws Exception {
        ArrayList<Token> result = new ArrayList<>();

        for (int i = 0; i < source.length(); i++) {
            String curr = String.valueOf(source.charAt(i));

            switch (curr) {
                case "(":
                    result.add(new Token(Token.TokenTypes.OpenParen, curr));
                    break;
                case ")":
                    result.add(new Token(Token.TokenTypes.CloseParen, curr));
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    result.add(new Token(Token.TokenTypes.BinaryOperator, curr));
                    break;
                case "=":
                    result.add(new Token(Token.TokenTypes.Equals, curr));
                    break;
                default:
                    if (isWhitespace(source.charAt(i))) {
                        break;
                    }

                    if (isDigit(source.charAt(i))) {
                        String number = "";

                        int j = i;

                        while (j < source.length() && isDigit(source.charAt(j))) {
                            number = number.concat(String.valueOf(source.charAt(j++)));
                        }

                        result.add(new Token(Token.TokenTypes.Number, number));

                        i = j - 1;
                        break;
                    }

                    if (isAlphabetic(source.charAt(i))) {
                        String identifier = "";

                        int j = i;

                        while (j < source.length() && isAlphabetic(source.charAt(j))) {
                            identifier = identifier.concat(String.valueOf(source.charAt(j++)));
                        }

                        Token.TokenTypes keyword = getKeywordTokenType(identifier);
                        result.add(new Token(Objects.requireNonNullElse(keyword, Token.TokenTypes.Identifier), identifier));

                        i = j - 1;
                        break;
                    }

                    throw new Exception("Unrecognized character!");
            }
        }

        return result;
    }
}
