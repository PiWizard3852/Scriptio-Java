package org.scriptio.test;

import org.scriptio.parser.Lexer;
import org.scriptio.parser.Token;

import java.util.LinkedList;

import static org.scriptio.util.PrintUtils.printTokens;

public class LexerTest {
    public static  void main(String[] args) throws Exception {
        String source = "fac test = 35;";

        Lexer lexer = new Lexer(source);
        LinkedList<Token> tokens = lexer.lex();

        printTokens(tokens);
    }
}
