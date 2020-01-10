class Solution {
    // Approach 1 - Brute Force
    // The first approach which I can come up with is to use brute force to iterate over 
    // every character in the string and check if the substring before it is a palindrome
    // we need to use two loops to find all possible substrings, 
    // for each substring we need O(n) time to verify, in total - O(n^3)
    
    // Approach 2 - Expand Aroung Center
    // It's very similar to 42.Trapping Rain Water
    // !!!! the number of centers is "2n - 1"
    // time - O(n^2) space - O(1)
    public String longestPalindrome1(String s) {
        String res = "";
        int n = s.length();
        int start = 0, end = 0;
        if (s == null || n == 0) return res;
        
        for (int i = 0; i < n; i++) {
            // expand to both sides from current character
            char c = s.charAt(i);
            
            // two cases:
            int l1 = expandCenter(s, i, i);
            int l2 = expandCenter(s, i, i + 1);
            
            int max = Math.max(l1, l2);
            if (max > end - start + 1) {
                start = i - (max - 1) / 2;
                end = i + max / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    
    public int expandCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            right++;
            left--;
        }
        // !!!
        // here the right and left has moved one step further, so need to subtract 1
        return right - left - 1;
    }
    
    // Approach 3 - Longest Common Substring - DP
    // Reverse S and become S'. Find the longest common substring between S and S' 
    // which must also be the longest palindromic substring.
    // !!! some problematic case: "aacdefcaa"
    // dp[i][j] = dp[i - 1][j - 1] + 1
    // Time - O(n^2) space - O(n^2), can be reduced to O(n)
    public String longestPalindromeNotPass(String s) {
        int n = s.length();
        if (n == 0) return "";
        int[][] dp = new int[n + 1][n + 1];
        int maxIdx = 0, maxLen = 0;
        
        // find the longest common substring
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i-1) == s.charAt(n - j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen){
                        maxIdx = i;
                        maxLen = dp[i][j];
                    }
                }
            }
        }
        return s.substring(maxIdx - maxLen, maxIdx);
    }
    
    // Approach 4 - DP
    // Consider the case "ababa", we already know "bab" is a palindrome, "ababa" is also
    // a palindrome sinc the left and right end letters are the same
    // dp[left,right] = dp[left + 1, right - 1] and (s(left) == s(right))
    // time - O(n^2) space - O(n^2)
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int maxLen = 0, start = 0;
        
        for (int i = n - 1; i >= 0; i--) { // left
            for (int j = i; j < n; j++) {  // right
                
                dp[i][j] = (s.charAt(i) == s.charAt(j)) 
                    && (j - i < 3 // if window is <= 3, just end chars should match
                    || dp[i + 1][j - 1]);  // if window > 3, previous status should be palindrome too
                
                // update the max
                if (dp[i][j] && j - i + 1 > maxLen){
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, maxLen + start);
    }
    
}
