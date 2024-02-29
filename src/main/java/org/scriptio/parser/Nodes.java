package org.scriptio.parser;

import java.util.LinkedList;

public class Nodes {
    public enum NodeTypes {
        Program,
        VariableDeclaration,
        VariableDeclarator,
        Identifier,
        UpdateExpression,
        AssignmentExpression,
        BinaryExpression,
        Literal,
    }

    public static abstract class Node {
        NodeTypes type;
    }

    public static class Program extends Node {
        public NodeTypes type = NodeTypes.Program;
        public LinkedList<Node> body = new LinkedList<>();
    }

    public static class VariableDeclaration extends Node {
        public NodeTypes type = NodeTypes.VariableDeclaration;
        public boolean mutable;
        public VariableDeclarator declaration;

        public VariableDeclaration(
            boolean mutable,
            VariableDeclarator declaration
        ) {
            this.mutable = mutable;
            this.declaration = declaration;
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
    }

    public static class Identifier extends Node {
        public NodeTypes type = NodeTypes.Identifier;
        public String name;

        public Identifier(String name) {
            this.name = name;
        }
    }

    public static class UpdateExpression extends Node {
        public NodeTypes type = NodeTypes.UpdateExpression;
        public Identifier id;
        public String operator;

        public UpdateExpression(Identifier id, String operator) {
            this.operator = operator;
            this.id = id;
        }
    }

    public static class AssignmentExpression extends Node {
        public NodeTypes type = NodeTypes.AssignmentExpression;
        public Identifier id;
        public String operator;
        public Literal value;

        public AssignmentExpression(
            Identifier id,
            String operator,
            Literal value
        ) {
            this.id = id;
            this.operator = operator;
            this.value = value;
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
    }

    public static class Literal extends Node {
        public NodeTypes type = NodeTypes.Literal;
        public String value;

        public Literal() {
        }

        public Literal(String value) {
            this.value = value;
        }
    }
}
