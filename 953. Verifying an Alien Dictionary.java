class Solution {
    // Approach 1 - Brute Force
    // time: O(n * k), k is the maximum length of word
    // space: O(1)
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length <= 1) return true;
        
        for (int i = 0; i < words.length - 1; i++) {
            String first = words[i];
            String second = words[i + 1];
            int idx = 0;
            
            // find the first different letter in two strings
            while (idx < first.length() && idx < second.length() && first.charAt(idx) == second.charAt(idx)) {
                idx++;
            }
            
            // the second ends first (second is shorter than the first)
            if (idx != first.length() && idx == second.length()) {
                return false;
            }
            
            int idx1 = order.indexOf(first.charAt(idx));
            int idx2 = order.indexOf(second.charAt(idx));
            if (idx1 > idx2)
                return false;
            
        }
        return true;
    }
}


