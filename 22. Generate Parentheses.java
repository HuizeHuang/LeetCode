class Solution {

    // Approach 1 - generate all possible parentheses and exclude all invalid combinations
    // there are 2^(2*n) combinations, then we need (n + 2n)/2 times to check if each combination is valid
    // time O(1.5n * 2^(2*n)) space O(n * 2^(2*n))
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList();
        generateAll(0, res, new char[2 * n]);
        return res;
    }
    
    public void generateAll(int pos, List<String> res, char[] curr) {
        if (pos == curr.length) {
            if (isValid(curr)) {
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
    
    // Approach 2 - backtracking
    // time space
    
}
