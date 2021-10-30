package org.compiler;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;
import java.util.Stack;

public  class RPNStacker {
    public static void main(String[] args) throws FileNotFoundException {
        Stack<Integer> stack = new Stack<>();

        File entriesPath = getEntries();
        File[] entries = entriesPath.listFiles();
        
        for (File file : entries) {
            Scanner entry = new Scanner(file);
            
            while(entry.hasNext()){
                if(entry.hasNextInt()){
                    stack.push(entry.nextInt());
                } else {
                    char operator = entry.next().charAt(0);
                    
                    int operandR = stack.pop();
                    int operandL = stack.pop();

                    stack.push(evaluateExpression(operandL, operandR, operator));
                }                
            }

            System.out.println("Resultado da expressão no arquivo " + file.getName() + ": " + stack.pop());
            entry.close();
        }
    }

    private static Integer evaluateExpression(int left, int right, char operator) {
        switch(operator){
            case '+':
                return left + right;
            case '-':
                return left - right;
            case '*':
                return left * right;
            case '/':
                return left / right;
            default:
                return null;
        }
    }

    private static File getEntries() {
        URL url = RPNStacker.class.getClassLoader().getResource("entries");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (Exception e) {
            file = new File(url.getPath());
        }

        return file;
    }

}
