package org.scriptio.test;

import org.scriptio.parser.Lexer;
import org.scriptio.parser.Nodes;
import org.scriptio.parser.Parser;
import org.scriptio.parser.Token;

import java.util.LinkedList;

public class ParserTest {
    public static void main(String[] args) throws Exception {
        String source = "";

        Lexer lexer = new Lexer(source);
        LinkedList<Token> tokens = lexer.lex();

        Parser parser = new Parser(tokens);
        Nodes.Program ast = parser.parse();

        System.out.println(ast);
    }
}
