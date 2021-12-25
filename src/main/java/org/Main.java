package org;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.compiler.RPNStacker;
import org.interpreter.Interpreter;
import org.scanner.Scanner;
import org.token.Token;

public class Main {
    
    public static void main(String[] args) throws IOException {
        RPNStacker stacker = new RPNStacker();

        File entriesPath = new File("src/main/resources/entries");
        File[] entries = entriesPath.listFiles();
        
        for (File file : entries) {
            Interpreter interpreter = new Interpreter();
            ArrayList<Token> tokensList = Scanner.scanning(file, interpreter);
            stacker.evaluateExpression(tokensList, interpreter, file.getName());
        }
    }
}
