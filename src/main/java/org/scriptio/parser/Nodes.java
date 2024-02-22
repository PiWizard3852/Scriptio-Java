package org.scriptio.parser;

import java.util.LinkedList;

public class Nodes {
    public enum NodeTypes {
        Program,
        VariableDeclaration,
        VariableDeclarator,
        FunctionDeclaration,
        Identifier,
        BinaryExpression,
        Literal,
        BlockStatement,
        CallExpression,
    }

    public static abstract class Node {
        NodeTypes type;

        public String toString(int indent) {
            return super.toString();
        };
    }

    public static class Program extends Node {
        public NodeTypes type = NodeTypes.Program;
        public LinkedList<Node> body = new LinkedList<>();

        public String toString() {
            String result = type.name() + ": {";

            for (int i = 0; i < body.size(); i++) {
                result = result.concat("\n\t" + body.get(i).toString(1));

                if (i < body.size() - 2) {
                    result = result.concat("\n");
                }
            }

            result += "\n}";

            return result;
        }
    }

    public static class VariableDeclaration extends Node {
        public NodeTypes type = NodeTypes.VariableDeclaration;
        public boolean mutable;
        public VariableDeclarator declaration;

        public VariableDeclaration(boolean mutable, VariableDeclarator declaration) {
            this.mutable = mutable;
            this.declaration = declaration;
        }

        public String toString(int indent) {
            String result = type.name() + ": {";

            result += "\n";

            for (int i = 0; i <= indent; i++) {
                result = result.concat("\t");
            }

            result += "mutable: " + mutable;

            result += "\n";

            if (declaration != null) {
                for (int i = 0; i <= indent; i++) {
                    result = result.concat("\t");
                }

                result += "declaration: " + declaration.toString(indent + 1);

                result += "\n";
            }

            for (int i = 1; i <= indent; i++) {
                result = result.concat("\t");
            }

            result += "}";

            return result;
        }
    }

    public static class VariableDeclarator extends Node {
        public NodeTypes type = NodeTypes.VariableDeclarator;
        public Identifier id;
        public Literal init;

        public VariableDeclarator(Identifier id, Literal init) {
            this.id = id;
            this.init = init;
        }

        public String toString(int indent) {
            String result = type.name() + ": {";

            result += "\n";

            for (int i = 0; i <= indent; i++) {
                result = result.concat("\t");
            }

            result += "id: " + id.toString(indent + 1);

            result += "\n";

            if (init != null) {
                for (int i = 0; i < indent; i++) {
                    result = result.concat("\t");
                }

                result += "init: " + init.toString(indent + 1);

                result += "\n";
            }

            for (int i = 1; i <= indent; i++) {
                result = result.concat("\t");
            }

            result += "}";

            return result;
        }
    }

    public static class FunctionDeclaration extends Node {
        public NodeTypes type = NodeTypes.FunctionDeclaration;
        public Identifier id;
        public LinkedList<Identifier> params = new LinkedList<>();
        public BlockStatement body;
    }

    public static class Identifier extends Node {
        public NodeTypes type = NodeTypes.Identifier;
        public String name;

        public Identifier(String name) {
            this.name = name;
        }

        public String toString(int indent) {
            String result = type.name() + ": {";

            result += "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "name: " + name;

            result += "\n";

            for (int i = 1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "}";

            return result;
        }
    }

    public static class Literal extends Node {
        public NodeTypes type = NodeTypes.Literal;
        public String value;

        public Literal() { }

        public Literal(String value) {
            this.value = value;
        }

        public String toString(int indent) {
            String result = type.name() + ": {";

            result += "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "value: " + value;

            result += "\n";

            for (int i = 1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "}";

            return result;
        }
    }

    public static class BinaryExpression extends Literal {
        public NodeTypes type = NodeTypes.BinaryExpression;
        public String operator;
        public Literal left;
        public Literal right;

        public BinaryExpression(String operator, Literal left, Literal right) {
            this.operator = operator;
            this.left = left;
            this.right = right;
        }

        public String toString(int indent) {
            String result = type.name() + ": {";

            result += "\n";

            for (int i = 0; i < indent + 1; i++) {
                result = result.concat("\t");
            }

            result += "left: " + left.toString(indent + 2) + "\n";

            for (int i = 0; i < indent + 1; i++) {
                result = result.concat("\t");
            }

            result += "operator: " + operator + "\n";

            for (int i = 0; i < indent + 1; i++) {
                result = result.concat("\t");
            }

            result += "right: " + right.toString(indent + 2);

            result += "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "}";

            return result;
        }
    }

    public static class BlockStatement extends Node {
        public NodeTypes type = NodeTypes.BlockStatement;
        public LinkedList<Node> body = new LinkedList<>();
    }

    public static class CallExpression extends Node {
        public NodeTypes type = NodeTypes.CallExpression;
        public Identifier callee;
        public LinkedList<Identifier> arguments;
    }
}
