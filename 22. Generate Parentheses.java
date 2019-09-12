class Solution {
    /*
    // Approach 1 - generate all possible parentheses and exclude all invalid combinations
    // there are 2^(2*n) combinations, then we need (n + 2n)/2 times to check if each combination is valid
    // time O(1.5n * 2^(2*n)) space O(n * 2^(2*n))
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        generateAll(0, res, new char[2 * n]);
        System.out.println(res.size());
        return res;
    }
    
    public void generateAll(int pos, List<String> res, char[] curr) {
        if (pos == curr.length) {
            if (isValid(curr)) { // remove this "if" if we want to print all combinations
                res.add(new String(curr));
            }
        }  
        else {
            curr[pos] = '(';
            generateAll(pos + 1, res, curr);
            curr[pos] = ')';
            generateAll(pos + 1, res, curr);
        }
        
    }
    
    public boolean isValid(char[] curr) {
        int leftCount = 0;
        for (char c : curr) {
            if (c == '(')
                leftCount++;
            else
                leftCount--;
            if (leftCount < 0)
                return false;
        }
        
        return leftCount == 0;  
    }
    */
    
    // Approach 2 - backtracking
    // Recursion with memoization, keep track of the number of opening and closing brackets
    // we have placed so far.
    // time approximately 2^n (b^d), where b is branching factor and d is maximum depth of recursion
    // space 2^n
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        backtrack(n, "", res, 0, 0);
        return res;
    }
    
    public void backtrack(int n, String curr, List<String> res, int open, int close) {
        if (curr.length() == 2 * n) {
            res.add(curr);
            return;
        }
        
        //We can add an opening bracket if we still have one (of n) left to place. 
        if (open < n) {
            backtrack(n, curr + "(", res, open + 1, close);
        }
        // We can add a closing bracket if it would not exceed the number of opening brackets.
        if (close < open) {
            backtrack(n, curr + ")", res, open, close + 1);
        }
    }
}





