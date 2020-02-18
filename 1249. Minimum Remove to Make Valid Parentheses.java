class Solution {
    // Approach 1 - Stack
    // 1. Remove "(" if it is left on stack at end
    // 2. Remove ")" if current stack is empty
    // time: O(n)
    // space: O(n)
    public String minRemoveToMakeValid1(String s) {
        Stack<Integer> stack = new Stack();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            char curr = sb.charAt(i);
            if (curr == '(') 
                stack.push(i);
            else if (curr == ')') {
                if (stack.isEmpty()) {
                    sb.deleteCharAt(i);
                    i--; //index can be changed as removing
                }
                else
                    stack.pop();  
            }    
        }
        while (!stack.isEmpty()) {
            sb.deleteCharAt(stack.pop());
        }
        return sb.toString();
    }
    
    // Approach 2 - Two parse stringBuilder
    // one stringbuilder deleting all invalid ) starting from left
    // one stringbuilder deleting all invalid ( starting from right
    // time: O(2 * n)
    // space: O(1)
    public String minRemoveToMakeValid2(String s) {
        StringBuilder sb = new StringBuilder(s);
        
        removeInvalid(sb, '(', ')');
        removeInvalid(sb.reverse(), ')', '(');
        return sb.reverse().toString();
        
    }
    
    public void removeInvalid(StringBuilder sb, char open, char close) {
        int balance = 0;
        for (int i = 0; i < sb.length(); i++) {
            char curr =  sb.charAt(i);
            if (curr == open)
                balance++;
            else if (curr == close) {
                balance--;
                if (balance < 0) {
                    sb.deleteCharAt(i);
                    i--; //delete character will change the length
                    balance++;
                }
            }
        }
    }
    
    
    // Approach 3 - One pass StringBuilder
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder(s);
        int balance = removeInvalid2(sb, '(', ')');
        
        int i = sb.length() - 1;
        while (balance > 0 && i >= 0) {
            if (sb.charAt(i) == '(') {
                balance--;
                sb.deleteCharAt(i);
            }
            i--;
        }
        return sb.toString();
    }
    
    public int removeInvalid2(StringBuilder sb, char open, char close) {
        int balance = 0;
        for (int i = 0; i < sb.length(); i++) {
            char curr =  sb.charAt(i);
            if (curr == open)
                balance++;
            else if (curr == close) {
                balance--;
                if (balance < 0) {
                    sb.deleteCharAt(i);
                    i--;
                    balance++;
                }
            }
        }
        return balance;
    }
}
