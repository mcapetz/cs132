import java.util.Scanner;

public class claireParse {

    static Scanner scanner;
    static String input;
    static Token token;

    // define tokens based on terminals
    // { } System.out.println ( ) ; if else while true false !
    // include EOF
    public enum Token {
        L_BRACKET, R_BRACKET, SYSTEM, L_PARENS, R_PARENS, SEMICOLON, IF, ELSE, WHILE, TRUE, FALSE, NOT, EOF
    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        StringBuilder inputBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            inputBuilder.append(scanner.nextLine().trim());
            inputBuilder.append("\n"); // Add a newline to separate lines (optional)
        }
        input = inputBuilder.toString();
        goal();

    }

    public static Token next_token() {
        // returns the next token
        input = input.trim();

        // consumed all tokens
        if (input.isEmpty()) {
            return Token.EOF;
        }

        // getting the next character
        // we always start at index 0 because continually eat the ones we've read
        char next_char = input.charAt(0);

        // single character ones
        switch (next_char) {
            case '{':
                input = input.substring(1); // Consume 1
                return Token.L_BRACKET;
            case '}':
                input = input.substring(1);
                return Token.R_BRACKET;
            case '(':
                input = input.substring(1);
                return Token.L_PARENS;
            case ')':
                input = input.substring(1);
                return Token.R_PARENS;
            case ';':
                input = input.substring(1);
                return Token.SEMICOLON;
            case '!':
                input = input.substring(1);
                return Token.NOT;
            case 'S':
                if (input.startsWith("System.out.println")) {
                    input = input.substring("System.out.println".length()); // Consume the keyword
                    return Token.SYSTEM;
                }
            case 'i':
                if (input.startsWith("if")) {
                    input = input.substring(2); // Consume the keyword
                    return Token.IF;
                } else {
                    return null;
                }
            case 'e':
                if (input.startsWith("else")) {
                    input = input.substring(4); // Consume the keyword
                    return Token.ELSE;
                } else {
                    return null;
                }
            case 'w':
                if (input.startsWith("while")) {
                    input = input.substring(5); // Consume the keyword
                    return Token.WHILE;
                } else {
                    return null;
                }
            case 't':
                if (input.startsWith("true")) {
                    input = input.substring(4); // Consume the keyword
                    return Token.TRUE;
                } else {
                    return null;
                }
            case 'f':
                if (input.startsWith("false")) {
                    input = input.substring(5); // Consume the keyword
                    return Token.FALSE;
                } else {
                    return null;
                }
        }
        return null;

    }

    // eat terminals
    public static void eat(Token a) {
        if (token == a) {
            token = next_token();
        } else {
            System.out.println("Parse error");
            System.exit(1);
        }
    }

    public static void goal() {
        token = next_token();
        S();
        eat(Token.EOF);
        System.out.println("Program parsed successfully");
        System.exit(0);
    }

    // S ::= { L } | System.out.println ( E ) ; | if ( E ) S else S | while ( E ) S
    public static void S() {
        // L case
        if (token == Token.L_BRACKET) {
            eat(Token.L_BRACKET);
            L();
            eat(Token.R_BRACKET);
        }
        // System case
        else if (token == Token.SYSTEM) {
            eat(Token.SYSTEM);
            eat(Token.L_PARENS);
            E();
            eat(Token.R_PARENS);
            eat(Token.SEMICOLON);
        }
        // if case
        else if (token == Token.IF) {
            eat(Token.IF);
            eat(Token.L_PARENS);
            E();
            eat(Token.R_PARENS);
            S();
            eat(Token.ELSE);
            S();
        }
        // while case
        else if (token == Token.WHILE) {
            eat(Token.WHILE);
            eat(Token.L_PARENS);
            E();
            eat(Token.R_PARENS);
            S();
        }
        // nothing
        else {
            System.out.println("Parse error");
            System.exit(1);
        }
    }

    // L ::= S L | ϵ
    public static void L() {
        // S case
        // what can S match to?
        // FIRST
        if (token == Token.L_BRACKET || token == Token.SYSTEM || token == Token.WHILE || token == Token.IF) {
            // recursively call
            S();
            L();
        } else {
        } // epsilon

    }

    // E ::= true | false | ! E
    public static void E() {
        if (token == Token.TRUE) {
            eat(Token.TRUE);
        } else if (token == Token.FALSE) {
            eat(Token.FALSE);
        } else if (token == Token.NOT) {
            eat(Token.NOT);
            E();
        } else {
            System.out.println("Parse error");
            System.exit(1);
        }
    }

}
