package org.scriptio.parser;

import org.scriptio.parser.Nodes.*;

import java.util.LinkedList;

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
            program.body.add(parseStatement());
        }

        return program;
    }

    private Node parseStatement() throws Exception {
        return switch (curr.type) {
            case VariableDeclaration -> parseVariableDeclaration();
            default -> parseAssignmentExpression();
        };
    }

    private Node parseVariableDeclaration() throws Exception {
        next();

        boolean mutable = false;

        if (curr.type == Token.TokenTypes.Mutable) {
            mutable = true;
            next();
        }

        Token type = next();

        if (type.type != Token.TokenTypes.Type) {
            throw new Exception("Expected type following fac!");
        }

        Token id = next();

        if (id.type != Token.TokenTypes.Identifier) {
            throw new Exception("Expected identifier following type!");
        }

        Token next = next();

        if (next.type == Token.TokenTypes.SemiColon) {
            if (!mutable) {
                throw new Exception("Immutable variable must be initialized!");
            }

            return new VariableDeclaration(
                mutable,
                new VariableDeclarator(
                    new Identifier(id.value),
                    null
                )
            );
        }

        if (!next.value.equals("=")) {
            throw new Exception("Expected variable assignment or semicolon " +
                "following identifier!");
        }

        Node init;

        switch (type.value) {
            case "verbum" -> {
                if (curr.type != Token.TokenTypes.String) {
                    throw new Exception("Expected verbum!");
                }

                init = new Literal(next().value);
            }
            case "veredictumne" -> {
                if (curr.type != Token.TokenTypes.Boolean) {
                    throw new Exception("Expected veredictumne!");
                }

                init = new Literal(next().value);
            }
            case "numerus" -> {
                if (curr.type == Token.TokenTypes.String || curr.type == Token.TokenTypes.Boolean) {
                    throw new Exception("Expected numerus!");
                }

                init = parseAdditiveExpression();
            }
            default -> init = parseAssignmentExpression();
        }

        Token semicolon = next();

        if (semicolon.type != Token.TokenTypes.SemiColon) {
            throw new Exception("Expected semicolon following declarator!");
        }

        return new VariableDeclaration(
            mutable,
            new VariableDeclarator(
                new Identifier(id.value),
                (Literal) init
            )
        );
    }

    // type, mutability, and declaration will need to be checked at runtime
    private Node parseAssignmentExpression() throws Exception {
        Node id = parseAdditiveExpression();

        if (curr.type == Token.TokenTypes.AssignmentOperator) {
            String operator = next().value;
            Node value = parseAdditiveExpression();

            Token semicolon = next();

            if (semicolon.type != Token.TokenTypes.SemiColon) {
                throw new Exception("Expected semicolon following assignment!");
            }

            id = new AssignmentExpression(
                (Identifier) id,
                operator,
                (Literal) value
            );
        }

        if (curr.type == Token.TokenTypes.UpdateOperator) {
            String operator = next().value;

            Token semicolon = next();

            if (semicolon.type != Token.TokenTypes.SemiColon) {
                throw new Exception("Expected semicolon following assignment!");
            }

            id = new UpdateExpression(
                (Identifier) id,
                operator
            );
        }

        return id;
    }

    private Node parseAdditiveExpression() throws Exception {
        Node left = parseMultiplicativeExpression();

        while (curr.value.equals("+") || curr.value.equals("-")) {
            String operator = next().value;
            Node right = parseMultiplicativeExpression();

            left = new BinaryExpression(
                (Literal) left,
                operator,
                (Literal) right
            );
        }

        return left;
    }

    private Node parseMultiplicativeExpression() throws Exception {
        Node left = parsePrimaryExpression();

        while (curr.value.equals("*") || curr.value.equals("/") || curr.value.equals("%")) {
            String operator = next().value;
            Node right = parsePrimaryExpression();

            left = new BinaryExpression(
                (Literal) left,
                operator,
                (Literal) right
            );
        }

        return left;
    }

    private Node parsePrimaryExpression() throws Exception {
        return switch (curr.type) {
            case Identifier -> new Identifier(next().value);
            case String, Number, Boolean, Null -> new Literal(next().value);
            case OpenParen -> {
                next();

                Node innerNode = parseAdditiveExpression();

                Token last = next();

                if (last.type != Token.TokenTypes.CloseParen) {
                    throw new Exception("Expected corresponding closing " +
                        "parenthesis!");
                }

                yield innerNode;
            }
            default -> throw new Exception("Unexpected token!");
        };
    }
}
