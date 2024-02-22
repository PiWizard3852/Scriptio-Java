package org.scriptio.parser;

import java.util.LinkedList;
import java.util.Objects;

import static org.scriptio.parser.Token.getKeywordTokenType;

public class Lexer {
    String source;

    public Lexer(String source) {
        this.source = source;
    }

    private static boolean isWhitespace(char character) { return Character.isWhitespace(character); }

    private static boolean isQuote(char character) { return character == '\''; }

    private static boolean isAlphabetic(char character) { return Character.isAlphabetic(character); }

    private static boolean isDigit(char character) { return Character.isDigit(character); }

    public LinkedList<Token> lex() throws Exception {
        LinkedList<Token> result = new LinkedList<>();

        for (int i = 0; i < source.length(); i++) {
            String curr = String.valueOf(source.charAt(i));

            switch (curr) {
                case "(":
                    result.add(new Token(Token.TokenTypes.OpenParen, curr));
                    break;
                case ")":
                    result.add(new Token(Token.TokenTypes.CloseParen, curr));
                    break;
//                case "{":
//                    result.add(new Token(Token.TokenTypes.OpenBrace, curr));
//                    break;
//                case "}":
//                    result.add(new Token(Token.TokenTypes.CloseBrace, curr));
//                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    result.add(new Token(Token.TokenTypes.BinaryOperator, curr));
                    break;
                case "=":
                    result.add(new Token(Token.TokenTypes.Equals, curr));
                    break;
                case ";":
                    result.add(new Token(Token.TokenTypes.SemiColon, curr));
                    break;
                default:
                    if (isWhitespace(source.charAt(i))) {
                        break;
                    }

                    if (isQuote(source.charAt(i))) {
                        String string = "";

                        int j = i + 1;

                        while (j < source.length() && !isQuote(source.charAt(j))) {
                            string = string.concat(String.valueOf(source.charAt(j++)));
                        }

                        result.add(new Token(Token.TokenTypes.Quote, curr));
                        result.add(new Token(Token.TokenTypes.String, string));
                        result.add(new Token(Token.TokenTypes.Quote, curr));

                        i = j;
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

                    throw new Exception("Unrecognized character!");
            }
        }

        return result;
    }
}
