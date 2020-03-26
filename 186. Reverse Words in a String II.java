class Solution {
    // Approach 1 - Concatenate a string
    // time: O(n)
    // space: O(n)
    
    // Approach 2 - reverse the whole string, and then reverse the word
    // 注意写helper function
    // time: O(n/2 + n)
    // space: O(1)
    public void reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        int left = 0, right = 0;
        for (; right < s.length; right++) {
            if (s[right] == ' ') {
                reverse(s, left, right - 1);
                left = right + 1;
            }
        }
        reverse(s, left, right - 1);
    }
    
    public void reverse(char[] s, int left, int right) {
        while(left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
