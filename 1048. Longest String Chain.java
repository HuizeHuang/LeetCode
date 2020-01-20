
class Solution {
    // Approach 1 - DFS (without sorting)
    // all DP problem can be solved with dfs + memo
    // for each word here, searching for this word's longest chain and keep it in the memo
    // time: O(n)
    // space
    public int longestStrChain(String[] words) {
        // String: count
        HashMap<String, Integer> memo = new HashMap();
        Set<String> set = new HashSet();
        for(String word : words) set.add(word);
        int res = 0;
        // check for each word
        for (String word : words) {
            res = Math.max(res, dfsMemo(memo, set, word));
        }
        return res;
    }
    
    public int dfsMemo(Map<String, Integer> memo, Set<String> set, String word) {
        
        //have visited
        if (memo.containsKey(word)) return memo.get(word);
        
        int max = 0;
        // loop till the end of current word
        for (int i = 0; i < word.length(); i++) {
            String subword = word.substring(0, i) + word.substring(i + 1);
            if (set.contains(subword))
                max = Math.max(max, dfsMemo(memo, set, subword));  // we can't do +1 here because for the smallest word in the array, there is no predecessor, it won't execute here, so we need to put max + 1 below
        }
        memo.put(word, max + 1);
        return max + 1;
    }
    
    // Approach 2 - Sorting + Hashmap + DP 
    // Instead of adding a character, try deleting a character to form a chain in reverse. 
    // For each word in order of length, for each word2 which is word with one character removed, length[word2] = max(length[word2], length[word] + 1).
    // time: O(n * s), s is the longest word length
    // space: O(n)
    public int longestStrChain2(String[] words) {
        if (words == null || words.length == 0) return 0;
        Map<String, Integer> dp = new HashMap();
        // sort the array
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        int res = 0;
        
        for (String currWord : words) {
            
            int currWordMax = 0;
            for (int i = 0; i < currWord.length(); i++) {
                String subWord = currWord.substring(0, i) + currWord.substring(i+1);
                
                // dp transition formula
                currWordMax = Math.max(currWordMax, dp.getOrDefault(subWord, 0) + 1);
            }
            dp.put(currWord, currWordMax);
            res = Math.max(res, currWordMax);
        }
        return res;
    }
    
}





