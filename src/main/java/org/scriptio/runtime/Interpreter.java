package org.scriptio.runtime;

import org.scriptio.parser.Lexer;
import org.scriptio.parser.Nodes.Program;
import org.scriptio.parser.Parser;
import org.scriptio.parser.Token;

import java.io.FileReader;
import java.util.LinkedList;
import java.util.Scanner;

// Doesn't actually interpret, just fetching/lexing/parsing source from file
public class Interpreter {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            throw new Exception("Expected exactly one argument, source file " +
                "path!");
        }

        // Fetch source from specified path
        Scanner scanner = new Scanner(new FileReader("./examples/" + args[0]));

        // Convert the file to a String
        StringBuilder sb = new StringBuilder();

        while (scanner.hasNext()) {
            sb.append(scanner.next().concat(" "));
        }

        scanner.close();

        String source = sb.toString();

        // Tokenize source
        Lexer lexer = new Lexer(source);
        LinkedList<Token> tokens = lexer.lex();

        // Build an AST from source
        Parser parser = new Parser(tokens);
        Program ast = parser.parse();

        // Print ast instead of evaluating
        System.out.println(ast);
    }
}