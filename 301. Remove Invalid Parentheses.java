class Solution {
    // Approach 1 - DFS - 1
    // keep track of the number of open and close brackets
    // time
    // space
    public List<String> removeInvalidParentheses(String s) {
        // get the number of invalid open and close brackets
        int open = 0, close = 0;
        for (char c : s.toCharArray()) {
            if (c == '(')
                open++;
            else if (c == ')'){
                if (open == 0)
                    close++;
                else
                    open--;
            }
        }
        
        List<String> res = new ArrayList();
        dfs(res, s, open, close, 0);
        return res;
    }
    
    public void dfs(List<String> res, String s, int open, int close, int start) {
        if (open == 0 && close == 0 && isValid(s)) {
            res.add(s);
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            
            char curr = s.charAt(i);
            // if bracket is the same as last one, we only delete the first one
            if (i != start && curr == s.charAt(i - 1) && (curr == '(' || curr == ')'))
                continue;
            if (curr == ')' && close > 0) 
                dfs(res, s.substring(0, i) + s.substring(i + 1), open, close - 1, i);
            else if (curr == '(' && open > 0)
                dfs(res, s.substring(0, i) + s.substring(i + 1), open - 1, close, i);
        }
    }
    
    // check if all brackets in current string is valid
    public boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(')
                count++;
            else if (c == ')')
                count--;
            if (count < 0) return false;
        }
        return count == 0;
    }
    // Approach 2 - BFS
    
}
