package org;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.compiler.RPNStacker;
import org.scanner.Scanner;
import org.token.Token;

public class Main {
    
    public static void main(String[] args) throws IOException {
        RPNStacker stacker = new RPNStacker();

        File entriesPath = new File("src/main/resources/entries");
        File[] entries = entriesPath.listFiles();
        
        for (File file : entries) {
            ArrayList<Token> tokensList = Scanner.scanning(file);
            stacker.evaluateExpression(tokensList, file.getName());
        }
    }
}
