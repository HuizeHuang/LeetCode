class Solution {
    // Approach 1 - Brute Force + hashset
    // iterate over for i for (0, n-1) and j for (i, n-1)
    // everytime check the substring (put char one by one in hash set and check)
    // time - O(n^3), space - O(min(n, m)), n is the length of string s, m is the length of alphabet set
    
    // Approach 2 - Sliding Window + HashSet
    // time - O(2 * n) space - O(n)
    public int lengthOfLongestSubstring1(String s) {
        int i = 0, j = 0;
        int n = s.length();
        int maxLen = 0;
        Set<Character> set = new HashSet();
        
        while (i < n && j < n) {
            char c = s.charAt(j);
            
            if (set.add(c)) {   // not repeated
                j++;
                maxLen = Math.max(maxLen, j - i);
            }
            else {              // repeated
                set.remove(s.charAt(i));
                i++;
            }
        }
        return maxLen;
    }
    
    // Approach 3 - Sliding Window + HashMap
    // Since we move i one by one using hashset, we can use a hashmap to store the index 
    // of characters, and we kan skip to that index once we find the repeated character
    // time - O(n) space - O(min(m,n))
    public int lengthOfLongestSubstring2(String s) {
        int i = 0, j = 0;
        int n = s.length();
        Map<Character, Integer> map = new HashMap();
        int maxLen = 0;
        while (i < n && j < n) {
            if (!map.containsKey(s.charAt(j))) { // not repeated
                map.put(s.charAt(j), j++);
                maxLen = Math.max(maxLen, j - i);
            }
            else {  // repeated
                i = Math.max(i, map.get(s.charAt(j)) + 1); // prevent i moves back
                map.remove(s.charAt(j));
            }
        }
        return maxLen;
    }
    
    // Approach 4 - Sliding Window + array
    // edge case: " " - space, should return 1
    // time - O(n) space - O(m)
    public int lengthOfLongestSubstring(String s) {
        int[] set = new int[128];    // according to the alphabet set
        Arrays.fill(set, -1);
        int i = 0, j = 0;
        int maxLen = 0;
        int n = s.length();

        while (i < n && j < n) {
            int index = s.charAt(j);
            if (set[index] == -1) {    // not repeated
                set[index] = j;
                j++;
                maxLen = Math.max(maxLen, j - i);
            }
            else {
                i = Math.max(i, set[index] + 1);
                set[index] = -1;
            }
        }
        return maxLen;
    }
}
