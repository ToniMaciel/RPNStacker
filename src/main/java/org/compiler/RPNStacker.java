package org.compiler;

import java.util.ArrayList;
import java.util.Stack;

import org.token.Token;
import org.token.TokenType;

public  class RPNStacker {
    public void evaluateExpression(ArrayList<Token> tokensList, String fileName) {
        Stack<Integer> stack = new Stack<>();

        if(tokensList != null){
            for (Token token : tokensList) {
                if (token.type == TokenType.NUM)
                    stack.push(Integer.parseInt(token.lexeme));
                else {
                    TokenType operator = token.type;
                    int operandR = stack.pop();
                    int operandL = stack.pop();
                    stack.push(evaluateOparation(operandL, operandR, operator));
                }
            }
    
            System.out.println("Resultado da expressão " + fileName + ": " + stack.pop());
        }           
        System.out.println("--------------------------------");
    }
    
    private static Integer evaluateOparation(int left, int right, TokenType operator) {
        switch(operator){
            case PLUS:
                return left + right;
            case MINUS:
                return left - right;
            case STAR:
                return left * right;
            case SLASH:
                return left / right;
            default:
                return null;
        }
    }

}
