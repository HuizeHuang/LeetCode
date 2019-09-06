class Solution {
    /*
    // Approach 1 - Hash table using sorted string
    // Use sorted string as key, their number of occurrences as values
    // time O(n * klogk) - n is length of array, k is length of maximum string
    // space O(n * k)
    public List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null || strs.length == 0) return new ArrayList<>();
        
        Map<String, List<String>> table = new HashMap<>();
        for(String s : strs) {
            char[] sorted = s.toCharArray();
            Arrays.sort(sorted);
            String sortedStr = String.valueOf(sorted);
            
            if (!table.containsKey(sortedStr)) 
                table.put(sortedStr, new ArrayList<String>());
            
            table.get(sortedStr).add(s);
        }
        // table的value可以直接提取出来
        return new ArrayList(table.values());
    }
    */
    
    // Approach 2 - Hash table using counting alphabet
    // time O(max(n, k)) space O(n * k)
    public List<List<String>> groupAnagrams(String[] strs) {
        // three parts
        // first we count each strng how many alphabet each
        // second turn the counting array into a string
        // third look up in the map
        
        if (strs == null || strs.length == 0) return new ArrayList();
        Map<String, List<String>> map = new HashMap();
        for (String s : strs) {
            // first part
            int[] counter = new int[26];
            for (char c : s.toCharArray()) {
                counter[c - 'a']++;
            }
            
            // second part
            String key = "";
            for (int count : counter) {
                key += "#" + Integer.toString(count);
            }
            
            // third part
            if (!map.containsKey(key))
                map.put(key, new ArrayList<String>());
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }
}
