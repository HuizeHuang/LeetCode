class Solution {
    // Approach 1 - backtracking - time limit exceeded
    // time: O((n^2) * (2^n)) 
    // Consider the worst case where ss = "aaaaaaa" 
    // and every prefix of ss is present in the dictionary of words, 
    // then the recursion tree can grow up to n^n 
    // space: O(n^3)
    // In worst case the depth of recursion tree can go up to nn 
    // and nodes can contain nn strings each of length nn.
    List<String> res = new ArrayList();
    public List<String> wordBreak1(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return null;
        Set<String> dict = new HashSet(wordDict);
        backtracking(s, dict, 0, 1, "");
        return res;
    }
    
    public void backtracking(String s, Set<String> dict, int last, int curr, String currStr) {
        if (last == s.length()) {
            res.add(currStr.substring(0, currStr.length() - 1));
            return;
        }
        if (curr > s.length()) return;
        
        String sub = s.substring(last, curr);
        if (dict.contains(sub)) {
            backtracking(s, dict, curr, curr+1, currStr+sub+" ");
        }
 
        backtracking(s, dict, last, curr+1, currStr);
 
    }
    
    // Approach 2 - DFS + memo
    // we are making use of a hashmap to store the results in the form of a key:value pair. In this hashmap, the keykey used is the starting index of the string currently considered and the valuevalue contains all the sentences which can be formed using the substring from this starting index onwards.
    // time: O(n * 2 ^ n)
    // Worst case when you all the n^2 prefixes given as wordDict. In that case every prefix lets you branch for solving a smaller subproblem of size n - i (i.e n - 1, n - 2, n - 3, ... 1).
    // space: O(n * 2 ^ n)
    
    Map<Integer, List<String>> map = new HashMap();
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return null;
        Set<String> dict = new HashSet(wordDict);
        return backMemo(s, dict, 0, map);
    }
    
    public List<String> backMemo(String s, Set<String> dict, int start, Map<Integer, List<String>> map) {
        
        if (map.containsKey(start))
            return map.get(start);
            
        List<String> curr = new ArrayList();
        // valid string not found
        if (start == s.length()) {
            curr.add("");
            return curr;
        }
        
        for (int end = start + 1; end <= s.length(); end++) {
            String sub = s.substring(start, end);
            if (dict.contains(sub)) {
                List<String> list = backMemo(s, dict, end, map);
 
                // for strings in map for current start index
                for (String str : list) {
                    curr.add(sub + (str == "" ? "" : " ") + str);
                }
            }
        }
        map.put(start, curr);
        return curr;
    }        
}

