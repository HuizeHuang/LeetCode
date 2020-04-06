class Solution {
    // Approach 1 - Convert to string
    // time: O(n)
    // space: O(1)
    public boolean isPalindrome2(int x) {
        if (x < 0) return false;
        String s = String.valueOf(x);
        /* 1. reverse
        StringBuilder sb = new StringBuilder(s);
        sb = sb.reverse();
        String rev = sb.toString()1
        */
        /*2 two pointers*/
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if(s.charAt(l) != s.charAt(r))
                return false;
            l++;
            r--;
        }
        return true;
    }
    
    // Approach 2 - Follow up - Math - divive & modulo
    // compare the reverted half with the other half to avoid overflow
    // time: O(log_10(x) / 2)
    // space: O(1)
    public boolean isPalindrome(int x) {
        // Special cases:
        // 1. When x < 0, x is not a palindrome.
        // 2. If the last digit of the number is 0, only 0 satisfy this property.
        if (x < 0 || (x % 10 == 0 && x != 0))  
            return false;
        
        int rev = 0;
        while (rev < x) {
            int mod = x % 10;
            rev = rev * 10 + mod;
            x /= 10;
        }
        
        // When the length is an odd number, we can get rid of the middle digit by revertedNumber/10
        // since the middle digit doesn't matter in palidrome(it will always equal to itself), we can simply get rid of it.
        return x == rev || x == rev/10;
    }
}
