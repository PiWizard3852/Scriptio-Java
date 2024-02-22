package org.scriptio.runtime;

import org.scriptio.parser.Nodes.Program;
import org.scriptio.parser.Parser;
import org.scriptio.parser.Lexer;
import org.scriptio.parser.Token;

import java.util.LinkedList;

public class Interpreter {
    public static void main(String[] args) throws Exception {
        // Get source from file

        // Tokenize source
        Lexer lexer = new Lexer("");
        LinkedList<Token> tokens = lexer.lex();

        // Build an AST from source
        Parser parser = new Parser(tokens);
        Program abstractSyntaxTree = parser.parse();

        // Walk ast, evaluate result
    }
}