class Solution {
    // Approach 1 - sliding window + hashMap
    // two pointers: 
    // right to expand the window when not satisfied
    // left to contract the window when satisfied
    // also use a map which keeps a count of all the unique characters in t.
    // time - O(|s| * |t|), space - O(|s| + |t|)
    public String minWindow1(String s, String t) {
        int sLen = s.length();
        if (sLen < t.length()) return "";
        
        // Build a map to keep a count of letters in T
        Map<Character, Integer> map = new HashMap();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int uniqueLen = map.size();
        
        // Use another map to keep a count of characters in current window
        Map<Character, Integer> curr = new HashMap();
        int count = 0;
        int left = 0, right = 0;
        String min = s + '0';
        
        while (right < sLen) {
            
            // not found
            char r = s.charAt(right);
            if (map.containsKey(r)) {
                curr.put(r, curr.getOrDefault(r, 0) + 1);
                if (curr.get(r).intValue() == map.get(r).intValue())
                    count++;
            }
            
            // if found
            while (left <= right && count == uniqueLen) {
                char l = s.charAt(left);
                if (right - left + 1 < min.length())
                    min = s.substring(left, right + 1);
                if (map.containsKey(l)) {
                    curr.put(l, curr.get(l) - 1);
                    // 说明去掉的这个单词对相等性造成了影响
                    if (curr.get(l).intValue() < map.get(l).intValue())
                        count--;
                }
                left++;
            }  // left ending
            
            right++;
        }
 
        return min.length() > sLen ? "" : min;
    }
    
    // Approach 2 - Sliding wondow + array
    // instead of using a hashmap, we can use an int array 
    // 
    public String minWindow(String s, String t) {
        if(s.length() < t.length()) return "";
        
        // build map (array)
        int[] map = new int[128];
        for (char c : t.toCharArray()) {
            map[c]++;
        }
        
        int left = 0, right = 0;
        String res = s + "a";
        int count = 0;
        
        while(right < s.length()) {
            // not found
            char r = s.charAt(right);
            map[r]--;
            
            if (map[r] >= 0) {
                count++;
            }
            
            while (left <= right && count == t.length()) {
                char l = s.charAt(left);
                map[l]++;
                
                // find smaller length
                if (right - left + 1 < res.length())
                    res = s.substring(left, right + 1);
                
                // current left is the one in T
                if (map[l] > 0)
                    count--;
                
                left++;
            }
            right++;
        }
        
        return res.length() > s.length() ? "" : res;
    }
}
