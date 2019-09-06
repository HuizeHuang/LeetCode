class Solution {
    /*
    // Approach 1 - Hash table
    // if the case only includes lowercase alphabets or only ascii code
    // we could use hash table
    // time O(2n) ~ O(n) space O(26 or 128)
    
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] alpha = new int[26];  // 所有的元素初始化为0
        for (int i = 0; i < s.length(); i++) {
            alpha[s.charAt(i) - 'a']++;
            alpha[t.charAt(i) - 'a']--;
        }
        
        for (int cnt : alpha) {
            if (cnt != 0) return false;
        }
        
        return true;
    }
    */
    
    // Approach 2 - sorting array
    // if unicode, we can use this method
    // time O(nlong) space O(1)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        
        Arrays.sort(str1);
        Arrays.sort(str2);
        
        return Arrays.equals(str1, str2);
    }
    
}
