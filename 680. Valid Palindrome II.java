class Solution {
    /* can i know if there are capital letters or spaces or punctuations in the string? */
    // Approach 1 - Two pointers
    // helper function
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s.substring(left + 1, right + 1)) || isPalindrome(s.substring(left, right));
            }
            else {
                left++;
                right--;
            }
        }
        return true;
    }
    
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right){
            if (s.charAt(left) != s.charAt(right))
                return false;
            else {
                left++;
                right--;
            }
        }
        return true;
    }
}
