package kadai1;

import java.util.Scanner;
import java.util.Stack;

public class kadai_middle {
    
    public static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }
    
    public static String infixToPostfix(String expression) {
        String result = "";
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);
            
            // If the scanned character is an operand, add it to output.
            if (Character.isLetterOrDigit(c)) {
                result += c;
            }
            // If the scanned character is an '(', push it to the stack.
            else if (c == '(') {
                stack.push(c);
            }
            // If the scanned character is an ')', pop and output from the stack 
            // until an '(' is encountered.
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                
                if (!stack.isEmpty() && stack.peek() != '(')
                    return "Invalid Expression"; // invalid expression                
                else
                    stack.pop();
            }
            else { // an operator is encountered
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())){
                    if(stack.peek() == '(')
                        return "Invalid Expression";
                    result += stack.pop();
                }
                stack.push(c);
            }
        }
        
        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            if(stack.peek() == '(')
                return "Invalid Expression";
            result += stack.pop();
        }
        
        return result;
    }
    
    public static double calculate(String expression) {
        Stack<Double> stack = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            
            if (Character.isDigit(ch)) {
                stack.push((double) (ch - '0'));
            } else {
                double b = stack.pop();
                double a = stack.pop();
                
                switch (ch) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(a / b);
                        break;
                    // 他の演算子が必要な場合、ここに追加
                }
            }
        }
        
        return stack.pop();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("数式を入力してください: ");
        String expression = scanner.nextLine();
        String ReversePoland = infixToPostfix(expression);
        double result = calculate(ReversePoland);
        System.out.println("計算結果: " + result);
    }
}
