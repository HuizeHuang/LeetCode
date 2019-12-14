class Solution {
    // Approach 1 - Brute Force
    // 循环两遍 - time O((|s| - |p|) * |p|)
    
    /*
    // Approach 2 - Sliding window
    // Using two arrays
    // one stores the count of letters in current sliding window
    // the other stores the count of the pattern
    // when two hashtable are identical, then we find an anagrams
    // time - O(|s| * 26), space - O(26*2) ~ O(1)
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList();
        if (s == null || p == null) return result;
        
        int[] countS = new int[26];
        int[] countP = new int[26];
        
        for (char c : p.toCharArray()) {
            countP[c - 'a']++;
        }
        int sw_size = p.length();
        // every time moving forward, pop the first one in sliding window 
        // and add the new one into it
        for (int i = 0; i<s.length()-sw_size+1; i++) {
            if (i == 0) {
                for (int k=0; k < sw_size; k++) {
                    countS[s.charAt(k) - 'a']++;
                }
            }
            
            // move sliding window
            else {
                countS[s.charAt(i-1)-'a']--;
                countS[s.charAt(i+sw_size-1)-'a']++;
            }
            // check the equality of two arrays
            if (compare(countP, countS)) result.add(i);
            
        }
        return result;
    }


    public boolean compare(int[] countP, int[] countS){
        for (int j = 0; j < 26; j++)
            if (countP[j] != countS[j]) 
                return false;
        return true;
        
    }
    */
    
    // Approach 3 - Sliding window
    // Using one array
    // count >= 0, this letter is needed, count < 0, this letter is far away from
    // the correct one
    // time O(n), space-O(26)
    // https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92059/O(n)-Sliding-Window-JAVA-Solution-Extremely-Detailed-Explanation
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList();
        if (s == null || p == null || s.length() < p.length()) return result;
        
        int[] count = new int[26];
        
        // start index of sliding window, end index of sliding window
        // differences between current string in sliding window and pattern string
        int start = 0, end = 0, len = p.length(), diff = len;
        
        // store the pattern first
        for (char c : p.toCharArray()) 
            count[c-'a']++;
        
        // start from the 0
        for (; end < len; end++) {
            // >= 0 means curr is in the pattern
            count[s.charAt(end) - 'a']--;
            if ( count[s.charAt(end) - 'a'] >= 0){
                diff--;
            }
                
        }
        
        if (diff==0)
            result.add(0);
        
        // start from the "start" index
        while(end < s.length()) {
            // 判断要被移除的滑动窗口的头元素是否是pattern里的，是的话需要把count减1
            // it means one step further to the pattern since the existed one is removed
            if (count[s.charAt(start) - 'a'] >= 0)
                diff++;
            // 之前在现在要移除的元素加1
            count[s.charAt(start) - 'a']++;
            start++;
            
            // 判断要新加进来的元素
            count[s.charAt(end) - 'a']--;
            if (count[s.charAt(end) - 'a'] >= 0)
                diff--;
            
            end++;
            
            if (diff == 0) result.add(start);
        }    
        return result;
    }
    
    
    // Approach 4 - Sliding Window - Hashmap
    //
    
}
