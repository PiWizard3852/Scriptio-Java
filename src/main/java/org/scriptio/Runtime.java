package org.scriptio;

import org.scriptio.lexer.Lexer;
import org.scriptio.lexer.Token;

import java.util.ArrayList;

public class Runtime {
    String source;

    Lexer lexer;

    public Runtime(String source) {
        this.source = source;
    }

    public void run() throws Exception {
        lexer = new Lexer(source);

        ArrayList<Token> lexedSource = lexer.lex();

        for (Token token : lexedSource) {
            System.out.println(token.type);
            System.out.println(token.value + "\n");
        }
    }
}