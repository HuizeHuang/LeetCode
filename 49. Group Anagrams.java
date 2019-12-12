class Solution {
    
    // Approach 1 - Hash table using sorted string
    // Use sorted string as key, list of strings that have the same sorted string as values
    // time O(n * klogk) - n is length of array, k is length of maximum string
    // sorting algorithm is Dual-Pivot Quicksort klog(k) is time-complexity
    // space O(n * k)
    public List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null || strs.length == 0) return new ArrayList<>();
        
        Map<String, List<String>> table = new HashMap<>();
        
        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String sorted = String.valueOf(chars);
            if (!table.containsKey(sorted)) 
                table.put(sorted, new ArrayList<String>());
            table.get(sorted).add(word);
        }

        return new ArrayList(table.values());
    }
        
    
    
    
    // Approach 2 - Categorize by Counting String
    // Hash table - Use #0#1#5#0... as key
    // time - O(nk), space - O(nk)
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList();
        Map<String, List<String>> table = new HashMap();
        int[] count = new int[26];
        
        for (String word : strs) {
            char[] chars = word.toCharArray();
            Arrays.fill(count, 0);
            for (char letter : chars) {
                count[letter - 'a']++;
            }
            StringBuilder sb = new StringBuilder("");
            for (int i : count) {
                sb.append('#');
                sb.append(i);
            }
            String key = sb.toString();
            
            if (!table.containsKey(key))
                table.put(key, new ArrayList());
            table.get(key).add(word);
            
        }
        return new ArrayList(table.values());
    }
    

   
}
