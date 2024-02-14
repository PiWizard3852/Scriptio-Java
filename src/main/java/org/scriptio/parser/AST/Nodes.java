package org.scriptio.parser.AST;

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

    abstract class Node {
        NodeTypes type;
    }

    public class Program extends Node {
        NodeTypes type = NodeTypes.Program;
        LinkedList<Node> body = new LinkedList<>();
    }

    public class InitNode extends Node { }

    public class VariableDeclaration extends Node {
        NodeTypes type = NodeTypes.VariableDeclaration;
        VariableDeclarator declaration;
    }

    public class VariableDeclarator extends Node {
        NodeTypes type = NodeTypes.VariableDeclarator;
        Identifier id;
        InitNode init;
    }

    public class FunctionDeclaration extends Node {
        NodeTypes type = NodeTypes.FunctionDeclaration;
        Identifier id;
        LinkedList<Identifier> params = new LinkedList<>();
        BlockStatement body;
    }

    public class Identifier extends Node {
        NodeTypes type = NodeTypes.Identifier;
        String name;
    }

    public class BinaryExpression extends InitNode {
        NodeTypes type = NodeTypes.BinaryExpression;
        String operator;
        Literal left;
        Literal right;
    }

    public class Literal extends InitNode {
        NodeTypes type = NodeTypes.Literal;
        String value;
    }

    public class BlockStatement extends Node {
        NodeTypes type = NodeTypes.BlockStatement;
        LinkedList<Node> body = new LinkedList<>();
    }

    public class CallExpression extends Node {
        NodeTypes type = NodeTypes.CallExpression;
        Identifier callee;
        LinkedList<Identifier> arguments;
    }
}
