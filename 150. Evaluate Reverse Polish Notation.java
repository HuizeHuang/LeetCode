



class Solution {
    // Approach 1 - stack
    // time: O(n)
    // space: O(n)
    public int evalRPN(String[] tokens) {
        
        Stack<Integer> stack = new Stack();
        
        for (String token : tokens) {
            if ("+-*/".contains(token)) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                
                int result = 0;
                switch (token) {
                    case "+": 
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num2 - num1;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        result = num2 / num1;
                        break;
                }
                stack.push(result);
            }
            else {
                stack.push(Integer.valueOf(token));
            }
        }
        return stack.pop();
    }
}





