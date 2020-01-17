class Solution {
    // Approach 1 - Brute force
    // keep iteraing from the start until we have the non-duplicated adjacent letters in the string
    // use str.substring()
    // time (n^m), m is the number of repeated letters, space - O(1)
    
    // Approach 2 - stack
    // push element one by one into the stack
    // each time when current letter in string is equal to top element in stack
    // we pop the top element and move curr pointer to next
    // time - O(n + #remaining letters) space - O(n)
    public String removeDuplicates1(String s) {
        if (s == null || s.length() == 0) return null;
        
        Stack<Character> stack = new Stack();
        for (char curr : s.toCharArray()) {
            if(!stack.isEmpty() && curr == stack.peek())
                stack.pop();
            else    // either the stack is empty or non equal to top char, we should both push element into the stack
                stack.push(curr);
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        return res;
    }
    
    // Approach 2 - string builder
    // well, instead of using the "real" stack data structure, we can use some other faster data structures to replace it
    // here we choose to use stringbuilder
    // time - O(n) space - O(n)
    public String removeDuplicates(String s) {
        StringBuilder curr = new StringBuilder();
        int len;
        for (char c : s.toCharArray()) {
            len = curr.length();
            if (len!= 0 && c == curr.charAt(len - 1)) {
                curr.deleteCharAt(len - 1);
            }
            else
                curr.append(c);
        }
        return curr.toString();
    }
}
