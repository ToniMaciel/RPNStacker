package org.scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.token.Token;
import org.token.TokenType;

public class Scanner {
    
    public static ArrayList<Token> scanning(File file) throws IOException {
        ArrayList<Token> tokensList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        while((line = br.readLine()) != null){
            if (line.matches("[0-9]+"))
                tokensList.add(new Token(TokenType.NUM, line));
            else if (line.matches("[+,-,/,*]"))
                tokensList.add(new Token(operationType(line), line));
            else{
                System.out.println("Error: Unexpected character at line " + (tokensList.size() + 1) + " in " + file.getName() + ": " + line);
                br.close();
                return null;
            }
        }
        br.close();

        System.out.println("Lista de tokens da expressão " + file.getName() + ": ");
        for (Token token : tokensList) {
            System.out.println(token.toString());
        }
        System.out.println();
        
        return tokensList;
    }

    private static TokenType operationType(String operator) {
        switch(operator){
            case "+":
                return TokenType.PLUS;
            case "-":
                return TokenType.MINUS;
            case "*":
                return TokenType.STAR;
            case "/":
                return TokenType.SLASH;
            default:
                return null;
        }
    }
}
