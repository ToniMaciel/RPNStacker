package org.scanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.interpreter.Interpreter;
import org.token.Token;
import org.token.TokenType;

public class Scanner {
    
    public static ArrayList<Token> scanning(File file, Interpreter interpreter) throws IOException {
        ArrayList<Token> tokensList = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = "";
        int lineNumber = 0;
        while((line = br.readLine()) != null){
            if (line.matches("[0-9]+"))
                tokensList.add(new Token(TokenType.NUM, line));
            else if (line.matches("[+,\\-,/,*]"))
                tokensList.add(new Token(operationType(line), line));
            else if (line.matches("^[a-z,A-Z]+[0-9]*->[0-9]+") || line.matches("^[a-z,A-Z]+[0-9]*->[a-z,A-Z]+[0-9]*")){
                String variable = line.split("->")[0];
                String reference = line.split("->")[1];

                if(!interpreter.addID(variable, reference)){
                    raiseScanError((lineNumber + 1), file.getName(), reference);
                    br.close();
                    return null;
                }
            } else if (line.matches("^[a-z,A-Z]+[0-9]*")){
                int value = interpreter.getID(line);

                if(value == -1){
                    raiseScanError((lineNumber + 1), file.getName(), line);
                    br.close();
                    return null;
                }
                else{
                    tokensList.add(new Token(TokenType.ID, Integer.toString(value)));
                }
            } else {
                raiseScanError((lineNumber + 1), file.getName(), line);
                br.close();
                return null;
            }

            lineNumber++;
        }
        br.close();

        System.out.println("Lista de tokens da expressão " + file.getName() + ": ");
        for (Token token : tokensList) {
            System.out.println(token.toString());
        }
        System.out.println();
        
        return tokensList;
    }

    private static void raiseScanError(int lineNumber, String fileName, String character) {
        System.out.println("Error: " + character + " cannot be resolved at line " + lineNumber + " in " + fileName);
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
