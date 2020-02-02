class Solution {
    // Approach 1 - Using java built-in methods
    public String reverseWords(String s) {
        s = s.strip();
        String[] strs = s.split("\\s+");
        String res = "";
        for (int i = strs.length - 1; i >= 0; i--)  {
            res += strs[i] + " ";
        }
        return res.strip();
    }
    // Approach 2 - reverse the whole string and then reverse each word
    // Approach 3 - push each word into a stack or deeque
}
