class Solution {
    // Approach 1 - DFS
    // If a star is present in the pattern, it will be in the second position pattern[1]. 
    // Then, we may ignore this part of the pattern, or delete a matching character in the text.
    // some edge cases: "ab" -> ".*c"
    // time: O((T + P) * 2 ^ (T + p/2))
    // space:O((T + P) * 2 ^ (T + p/2))
    public boolean isMatch1(String s, String p) {
        // if the remaining lengths are both 0, they can match, otherwise not
        if (p.length() == 0) return s.length() == 0;
        
        // check if the first characters can match
        // s.length() != 0 check for "ab", ".*c", there are extra character in p
        boolean first_match = s.length() != 0 && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0));
        
        // if there's a * in p
        if (p.length() >= 2 && p.charAt(1) == '*')
            // first to check extra chars in pattern
            return isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p));
        else
            return first_match && isMatch(s.substring(1), p.substring(1));
            
    }
    
    // Approach 1 - DFS with memoization
    // After DFS solution, we can think how we can use memo to reduce time complexity
    public boolean isMatch(String s, String p) {
        int S = s.length();
        int P = p.length();
        Boolean[][] memo = new Boolean[S+1][P+1];
        return dfs_with_memo(s, p, memo);
    }
    public boolean dfs_with_memo(String s, String p, Boolean[][] memo) {
        // base case
        if (p.length() == 0) return s.length() == 0;
        
        if (memo[s.length()][p.length()] != null) return memo[s.length()][p.length()];
        
        boolean first_match = (s.length() != 0) && (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0));
        
        if (p.length() >= 2 && p.charAt(1) == '*')
            return memo[s.length()][p.length()] = (dfs_with_memo(s, p.substring(2), memo) || (first_match && dfs_with_memo(s.substring(1), p, memo)));
        else
            return memo[s.length()][p.length()] = (first_match && dfs_with_memo(s.substring(1), p.substring(1), memo));
        
    }
    
    
    // Approach 2 - 
}
