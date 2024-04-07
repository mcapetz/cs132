import java.util.Scanner;

public class Parse {

    enum Token {
        LEFT_BRACE, RIGHT_BRACE, PRINTLN, LEFT_PAREN, RIGHT_PAREN,
        SEMICOLON, IF, ELSE, WHILE, TRUE, FALSE, NOT, EOF; 
    }

    static Token token;
    static String inputString;
    static int index;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder input = new StringBuilder();
        while(scanner.hasNextLine()) {
            input.append(scanner.nextLine());
            input.append("\n");
        }
        inputString = input.toString();
        inputString = inputString.replaceAll("\\s+", "");
        index = 0;
        goal();
    }


    static Token nextToken() {

        inputString = inputString.trim();
        
        if (index >= inputString.length()) {
            return Token.EOF;
        }
        
        char c = inputString.charAt(index++);
        // System.out.println("C: " + c);
        switch (c) {
            case '{': return Token.LEFT_BRACE;
            case '}': return Token.RIGHT_BRACE;
            case ';': return Token.SEMICOLON;
            case 'S': // Assuming "System.out.println"
                if (index + 17 <= inputString.length() && inputString.substring(index - 1, index + 17).equals("System.out.println")) {
                    index += 17;
                    // System.out.println("consumed the print");
                    // System.out.println(inputString.substring(index));
                    return Token.PRINTLN;
                }
                else {
                    break;
                }
            case '(': return Token.LEFT_PAREN;
            case ')': return Token.RIGHT_PAREN;
            case 'i': // Assuming "if"
                if (index + 1 <= inputString.length() && inputString.substring(index - 1, index + 1).equals("if")) {
                    index++;
                    return Token.IF;
                }
                break;
            case 'e': // Assuming "else"
                if (index + 3 <= inputString.length() && inputString.substring(index - 1, index + 3).equals("else")) {
                    index += 3;
                    return Token.ELSE;
                }
                break;
            case 'w': // Assuming "while"
                if (index + 4 <= inputString.length() && inputString.substring(index - 1, index + 4).equals("while")) {
                    index += 4;
                    return Token.WHILE;
                }
                break;
            case 't': // Assuming "true"
                if (index + 3 <= inputString.length() && inputString.substring(index - 1, index + 3).equals("true")) {
                    index += 3;
                    return Token.TRUE;
                }
                break;
            case 'f': // Assuming "false"
                if (index + 4 <= inputString.length() && inputString.substring(index - 1, index + 4).equals("false")) {
                    index += 4;
                    return Token.FALSE;
                }
                break;
            case '!': return Token.NOT;
        }
        return Token.EOF;
    }

    static void eat(Token expected) {
        if (token == expected) {
            token = nextToken();
        } else {
            error();
        }
    }

    static void error() {
        System.out.println("Parse error");
        System.exit(1);
    }

    static void goal() { // goal symbols are the terminal symbols
        token = nextToken();
        S(); // <- S is the start symbol
        eat(Token.EOF); 
        System.out.println("Program parsed successfully");
        System.exit(0);
    }

    // S ::= { L } | System.out.println ( E ) ; | if ( E ) S else S | while ( E ) S
    static void S() {
        if(token == Token.LEFT_BRACE) {
            eat(Token.LEFT_BRACE);
            L();
            eat(Token.RIGHT_BRACE);
        }
        else if(token == Token.PRINTLN) {
            eat(Token.PRINTLN);
            eat(Token.LEFT_PAREN);
            E();
            eat(Token.RIGHT_PAREN);
            eat(Token.SEMICOLON);
        } 
        else if(token == Token.IF) {
            eat(Token.IF);
            eat(Token.LEFT_PAREN);
            E();
            eat(Token.RIGHT_PAREN);
            S();
            eat(Token.ELSE);
            S();
        }
        else if(token == Token.WHILE) {
            eat(Token.WHILE);
            eat(Token.LEFT_PAREN);
            E();
            eat(Token.RIGHT_PAREN);
            S();
        }
        else {
            error();
        }
    }

    // L ::= S L | ϵ
    static void L() {
        if (token == Token.LEFT_BRACE || token == Token.PRINTLN || token == Token.IF || token == Token.WHILE) { // basically what S can match
            S(); // parse statement S
            L(); // recursively parse list L
        }
        else {} // this is how you handle eps
    }

    // E ::= true | false | ! E
    static void E() {
        if(token == Token.TRUE) {
            eat(Token.TRUE);
        }
        else if(token == Token.FALSE) {
            eat(Token.FALSE);
        }
        else if(token == Token.NOT) {
            eat(Token.NOT);
            E();
        }
        else {
            error();
        }
    }

}