package org.scriptio.lexer.util;

public class RomanNumerals {
    public static int value(char character) {
        return switch (character) {
            case 'i' -> 1;
            case 'v' -> 5;
            case 'x' -> 10;
            case 'l' -> 50;
            case 'c' -> 100;
            case 'd' -> 500;
            case 'm' -> 1000;
            default -> -1;
        };
    }

    public static String value(String input) {
        long result = 0;

        for (int i = 0; i < input.length(); i++) {
            int currValue = value(input.charAt(i));
            int nextValue = i + 1 < input.length() ? value(input.charAt(i + 1)) : -1;

            if (currValue >= nextValue) {
                result += currValue;
            } else {
                result -= currValue;
            }
        }

        return String.valueOf(result);
    }

    public static boolean isRoman(char character) {
        return value(character) != -1;
    }
}
