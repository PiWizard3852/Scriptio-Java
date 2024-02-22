package org.scriptio.parser;

import java.util.LinkedList;

import org.scriptio.parser.Nodes.*;

public class Parser {
    LinkedList<Token> tokens;

    int currIndex;
    Token curr;

    public Parser(LinkedList<Token> tokens) {
        this.tokens = tokens;

        if (!tokens.isEmpty()) {
            currIndex = 0;
            curr = tokens.get(currIndex);
        }
    }

    private Token next() {
        Token last = curr;

        currIndex++;

        if (currIndex < tokens.size()) {
            curr = tokens.get(currIndex);
        }

        return last;
    }

    public Program parse() throws Exception {
        Program program = new Program();

        while (currIndex < tokens.size()) {
            program.body.add(parseAdditiveExpression());
        }

        return program;
    }

    private Node parseAdditiveExpression() throws Exception {
        Node left = parseMultiplicativeExpression();

        while (curr.value.equals("+") || curr.value.equals("-")) {
            String operator = next().value;
            Node right = parseMultiplicativeExpression();

            left = new BinaryExpression(operator, (Literal) left, (Literal) right);
        }

        return left;
    }

    private Node parseMultiplicativeExpression() throws Exception {
        Node left = parsePrimaryExpression();

        while (curr.value.equals("*") || curr.value.equals("/")) {
            String operator = next().value;
            Node right = parsePrimaryExpression();

            left = new BinaryExpression(operator, (Literal) left, (Literal) right);
        }

        return left;
    }

    private Node parsePrimaryExpression() throws Exception {
        return switch (curr.type) {
            case Identifier -> new Identifier(next().value);
            case String, Number, Boolean, Null -> new Literal(next().value);
//            case OpenParen -> null;
            default -> throw new Exception("Unrecognized token!");
        };
    }
}
