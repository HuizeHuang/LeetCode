// Approach 1 - BFS - HashMap - queue
// time - O(k*n^2), spcae - O(n^2)
// 643ms, faster than 10%
// Hash map - key being all words, value being all possible next words
// Need a set to store all distinct values avoiding cycle 1->2->1
class Solution1 {

    public HashMap<String, List<String>> map = new HashMap<>();
    
    public void buildMap(String beginWord, List<String> wordList){
        
        for (String word : wordList){
            List<String> list = new ArrayList<String>();
            if (!map.containsKey(word)) {
                map.put(word, list);
            }
            for (String next : wordList) {
                if (diff(word, next)){
                    map.get(word).add(next);
                }
            }
        }
        
        if (!map.containsKey(beginWord)) {
            List<String> list = new ArrayList<String>();
            map.put(beginWord, list);
            for (String next : wordList) {
                if (diff(beginWord, next)){
                    map.get(beginWord).add(next);
                }
            }
        }
    }
    
    public boolean diff(String word, String next){
        
        int count = 0;
        
        for (int i = 0; i < word.length(); i++) {
            if(word.charAt(i) != next.charAt(i))
                count++;
        }
        return true ? count ==  1 : count != 1;
    }
    
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) return 0;
        
        Queue<String> queue = new LinkedList<>();
        HashSet<String> set = new HashSet<>();
        
        buildMap(beginWord, wordList);
        queue.add(beginWord);

        int level = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++){
                String currWord = queue.poll();
                
                if (currWord.equals(endWord))
                        return level;

                for (String next : map.get(currWord)){
                    if (!set.contains(next)){
                        set.add(currWord);
                        queue.add(next);
                    }  
                }
            }
            level++;
        }
        if (queue.isEmpty())
            return 0;
        return level;
    }
}

/**********************************************************************************/
// Approach 2 - BFS
// Two sets and a queue
// Find all possible words by replacing each of the letter through a-z
// Construct map on the fly
// time - O(n * l * 26) ~ O(n * l), n is the number of words, l is the length of word
// space - O(3 * n)

class Solution {
    
    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet(wordList);
        Set<String> visited = new HashSet();
        Queue<String> queue = new LinkedList();
        queue.add(beginWord);
        
        // Iterate as level grows
        for (int level = 1; !queue.isEmpty(); level++) {
            
            // iterate in each level
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                String currWord = queue.poll();
                if (currWord.equals(endWord)) 
                    return level;
                
                // iterate over each letter in current word
                for (int j = 0; j < currWord.length(); j++) {
                    char[] chars = currWord.toCharArray();
                    
                    // iterate over 26 letters through a - z
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == chars[j]) continue;
                        
                        chars[j] = c;
                        String curr = String.valueOf(chars);
                        if (dict.contains(curr) && visited.add(curr))
                            queue.add(curr);

                    }
                }
            }
        }
        return 0;
    }
    
    
    // Approach 3 - Bidirectional bfs
    // launch two simultaneous BFS. One from the beginWord and one from the endWord.
    // Termination condition for bidirectional search is finding a word which is already been seen by the parallel search.
    // use set as queue in bfs since hashset has O(1) - constains
    // time - b^(d/2) + b^(d/2) is much less than b^d. b is branch factor, d is depth.
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet(), endSet = new HashSet(), visited = new HashSet();
        Set<String> dict = new HashSet(wordList);
        
        beginSet.add(beginWord);
        if (dict.contains(endWord)) endSet.add(endWord);
        
        for (int level = 1; !beginSet.isEmpty(); level++) {
            
            // we work on the beginSet
            // whoever has the smaller length is the beginSet
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            
            // create a temporary set to store all current level's nodes' next nodes
            Set<String> temp = new HashSet();
            
            // iterate over current level
            for (String currWord : beginSet) {
                
                // iterate over every letter in current word
                for (int i = 0; i < currWord.length(); i++) {
                    
                    char[] chars = currWord.toCharArray();
                    
                    // iterate single position for 26 letters
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (chars[i] == c) continue;
                        chars[i] = c;
                        String str = String.valueOf(chars);
                        
                        // termination condition - the word has appeared in the other set
                        if (endSet.contains(str)) return level+1;
                        
                        if (dict.contains(str) && visited.add(str)){
                            temp.add(str);
                        }
                    }
                }
            }
            beginSet = temp;
        }
        return 0;
    }
    
    // Optimization
    // Use a hash map to remember the position where the current word differentiate than previous one
    // from 95% -> 98%
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        Set<String> beginSet = new HashSet(), endSet = new HashSet(), visited = new HashSet();
        Set<String> dict = new HashSet(wordList);
        
        // to optimize, remember the which position it changes from
        Map<String, Integer> pos = new HashMap();
        pos.put(beginWord, -1);
        pos.put(endWord, -1);
        
        beginSet.add(beginWord);
        if (dict.contains(endWord)) endSet.add(endWord);
        
        for (int level = 1; !beginSet.isEmpty(); level++) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }
            Set<String> temp = new HashSet();
            for (String currWord : beginSet) {
                char[] chars = currWord.toCharArray();
                
                for (int i = 0; i < currWord.length(); i++) {
                    if (i == pos.get(currWord)) continue;
                    char old = chars[i];
                    
                    for (char c = 'a'; c <='z'; c++) {
                        if (c == chars[i]) continue;
                        chars[i] = c;
                        
                        String str = String.valueOf(chars);
                        // !!! termination condition
                        if (endSet.contains(str)) return level+1;
                        
                        if (dict.contains(str) && visited.add(str)){
                            temp.add(str);
                            pos.put(str, i);
                        } 
                    }
                    chars[i] = old;
                }
            }
            beginSet = temp;
        }
        return 0;
    }
    
}






