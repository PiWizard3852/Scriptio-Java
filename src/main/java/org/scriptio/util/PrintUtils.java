package org.scriptio.util;

import org.scriptio.parser.Token;

import java.util.LinkedList;

public class PrintUtils {
    public static void printTokens(LinkedList<Token> input) {
        System.out.println("[");

        for (Token token : input) {
            System.out.println("\t{");
            System.out.print("\t\ttype: ");
            System.out.println(token.type);
            System.out.print("\t\tvalue: ");
            System.out.println(token.value);
            System.out.println("\t},");
        }

        System.out.println("]");
    }
}
