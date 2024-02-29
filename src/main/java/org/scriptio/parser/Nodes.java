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

        public String toString(int indent) {
            return super.toString();
        }
    }

    public static class Program extends Node {
        public NodeTypes type = NodeTypes.Program;
        public LinkedList<Node> body = new LinkedList<>();

        public String toString() {
            String result = "{\n";

            for (Node node : body) {
                result = "\n" + result.concat(node.toString(1));
            }

            result += "\n}";

            return result;
        }
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

        public String toString(int indent) {
            String result = "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "{\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "type: " + type + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "mutable: " + mutable + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "declaration: " + declaration.toString(indent + 1) + "\n";

            for (int i = 0; i < indent; i++) {
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
            String result = "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "{\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "type: " + type + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "id: " + id.toString(indent + 1) + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "init: " + init.toString(indent + 1) + "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "}";

            return result;
        }
    }

    public static class Identifier extends Node {
        public NodeTypes type = NodeTypes.Identifier;
        public String name;

        public Identifier(String name) {
            this.name = name;
        }

        public String toString(int indent) {
            String result = "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "{\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "type: " + type + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "name: " + name + "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "}";

            return result;
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

        public String toString(int indent) {
            String result = "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "{\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "type: " + type + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "id: " + id.toString(indent + 1) + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "operator: " + operator + "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "}";

            return result;
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

        public String toString(int indent) {
            String result = "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "{\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "type: " + type + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "id: " + id.toString(indent + 1) + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "operator: " + operator + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "value: " + value.toString(indent + 1) + "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "}";

            return result;
        }
    }

    public static class BinaryExpression extends Literal {
        public NodeTypes type = NodeTypes.BinaryExpression;
        public Literal left;
        public String operator;
        public Literal right;

        public BinaryExpression(Literal left, String operator, Literal right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        public String toString(int indent) {
            String result = "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "{\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "type: " + type + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "left: " + left.toString(indent + 1) + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "operator: " + operator + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "right: " + right.toString(indent + 1) + "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "}";

            return result;
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

        public String toString(int indent) {
            String result = "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "{\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "type: " + type + "\n";

            for (int i = -1; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "value: " + value + "\n";

            for (int i = 0; i < indent; i++) {
                result = result.concat("\t");
            }

            result += "}";

            return result;
        }
    }
}
