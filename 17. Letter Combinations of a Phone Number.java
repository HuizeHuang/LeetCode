class Solution {
    /*
    // Approach 1 - Backtracking
    // n = digis.length()
    // time O(n^3) space O(n)
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList();
        if (digits == null || digits.length() == 0) return res;
        
        String[] map = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        
        int n = digits.length();
        helper(digits, res, map, "");
        return res;
    }
    
    public void helper(String digits, List<String> res, String[] map, String curr) {
        if (curr.length() == digits.length()) {
            res.add(curr);
            return;
        }
        // !!!! 注意记录当前层数的是用curr.length()
        int index = digits.charAt(curr.length()) - '0';
        for (char ch : map[index].toCharArray()) {
            curr += ch;
            helper(digits, res, map, curr);
            curr = curr.substring(0, curr.length() - 1);
        }
    }
    */
    
    // Approach 2 - Iterative BFS
    // Using a queue, iterate while current length is no larger than current digit index
     public List<String> letterCombinations(String digits) {
         LinkedList<String> queue = new LinkedList();
         if (digits == null || digits.length() == 0) return queue;
         String[] map = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
         queue.add("");
         for (int i = 0; i < digits.length(); i++) {
             int mapIdx = digits.charAt(i) - '0';
             while (queue.peek().length() == i) {
                 String curr = queue.poll();
                 
                 for (char ch : map[mapIdx].toCharArray()) {
                     queue.add(curr + ch);
                 }
             }
         }
         return queue;
     }
}
