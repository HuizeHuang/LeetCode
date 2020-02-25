class Solution {
    // Approach 1 - Brute Force
    // time: O(n^2)
    
    // similar to 5. Longest Palindromic Substring
    // Approach 2 - Expand Around Center
    // time: O(n^2), expansion can be O(n) when all string is a palindrome
    // space: O(1)
    public int countSubstrings1(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res += expand(s, i, i);
            res += expand(s, i, i + 1);
        }
        return res;
    }
    
    public int expand(String s, int left, int right) {
        int len = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            len++;
        }
        return len;
    }
    
    // Approach 2 - DP
    // dp[left][right] = ((right - left < 3) || dp[left + 1][right - 1]) && s[left] == s[right]
    public int countSubstrings(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int res = 0;
        
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                dp[i][j] = ((j - i < 3) || dp[i + 1][j - 1]) && s.charAt(i) == s.charAt(j);
                if (dp[i][j])
                    res++;
            }
        }
        return res;
    }
}
