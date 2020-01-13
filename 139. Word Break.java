class Solution {
    // Approach 1 - dfs - TLE
    // time - O(n^n) worst case: "aaaaaaa", every prefix of s is present in the dictionary
    // space - O(n)
    public boolean wordBreak1(String s, List<String> wordDict) {
        return dfs(s, wordDict);
    }
    
    public boolean dfs(String s, List<String> wordDict) {
        if (s.length() == 0) return true;
        
        for (String word : wordDict) {
            int length = word.length();
            if (s.length() >= length) {
                String subString = s.substring(0, length);

                if (subString.equals(word)) {
                    if (dfs(s.substring(length), wordDict))
                        return true;
                }
            }
        }
        return false;
    }
    
    // Approach 2 - dfs + memo
    // instead of iterating the dictionary, we can iterate over the string, character by character
    // convert wordDict to hashset to obtain O(1) tiem check
    // time space
    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean[] memo = new Boolean[s.length()];
        Set<String> wordSet = new HashSet(wordDict);
        return dfsMemo(s, wordSet, 0, memo);
    }
    
    public boolean dfsMemo(String s, Set<String> wordSet, int start, Boolean[] memo) {
        if (start == s.length()) return true;
        if (memo[start] != null) return memo[start];
        
        // !! <=
        for (int end = start + 1; end <= s.length(); end++) {
            String curr = s.substring(start, end);
            if (wordSet.contains(curr)) {
                if (dfsMemo(s, wordSet, end, memo))
                    return memo[start] = true;
            }
        }
        return memo[start] = false;
    }
    
    // Approach 3 - bfs
    // Approach 4 - dp
}
