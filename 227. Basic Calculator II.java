class Solution {
    // Approach 1 - Stack
    // '-': stack.push -num
    // time: O(n)
    // space: O(n)
    public int calculate(String s) {
        Stack<Integer> stack = new Stack();
        int num = 0, res = 0;
        char lastSign = '+';
        
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (Character.isDigit(curr)) {
                num = num * 10 + (curr - '0');
            }
            if ((!Character.isDigit(curr) && curr != ' ') || i == s.length() - 1) {
                switch(lastSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        res = stack.pop() * num;
                        stack.push(res);
                        break;
                    case '/':
                        res = stack.pop() / num;
                        stack.push(res);
                        break;
                }
                num = 0;
                lastSign = curr;            
            }
        }
        
        int sum = 0;
        while(!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
}
