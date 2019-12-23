class Solution {
    // Approach 1 - bfs with queue
    // https://leetcode.com/problems/word-ladder-ii/discuss/40447/Share-two-similar-Java-solution-that-Accpted-by-OJ.
    // BFS + DFS
    // BFS to build the graph
    // DFS to find all paths in graph
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // BFS to build the graph
        // using queue + one direction
        if (wordList == null || wordList.size() == 0) return new ArrayList();
        
        // same as the word ladder 1
        Queue<String> queue = new LinkedList();
        queue.add(beginWord);
        Set<String> dict = new HashSet(wordList);
        dict.add(beginWord);
        List<List<String>> res = new ArrayList();
        
        // new structures to build graph
        Map<String, Integer> distance = new HashMap();  // used as visited in word ladder I
        distance.put(beginWord, 0);
        Map<String, List<String>> children = new HashMap(); //  可以记录所有子节点，也可以记录所有父节点 
        for (String w : dict) children.put(w, new ArrayList());
        boolean foundEnd = false;
        
        // start bfs
        while(!queue.isEmpty() && !foundEnd) {
            int size = queue.size();
            
            // process all current neighbors
            for (int i = 0; i < size; i++) {
                String currWord = queue.poll();
                char[] chars = currWord.toCharArray();
                
                // current word
                for (int j = 0; j < chars.length; j++) {
                    char old = chars[j];
                    
                    // current letter
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (chars[j] == c) continue;
                        chars[j] = c;
                        String newWord = String.valueOf(chars); 
                        
                        // find a new neighbor
                        if (dict.contains(newWord)) {
                            // add to children map
                            children.get(currWord).add(newWord);

                            // if the new neighbor has not shown up before
                            // we add the new one to distance map
                            // otherwise its distance is definitely larger than already stored one
                            if (!distance.containsKey(newWord)) {  
                                distance.put(newWord, distance.get(currWord) + 1);
                                if (endWord.equals(newWord))
                                    foundEnd = true;
                                else
                                    queue.add(newWord);
                            }
                        }
                    } // end of current letter
                    chars[j] = old;
                } // end of current word
            } // end of current neighbors
        } // end of bfs
        
        // Start dfs (backtracking) - output all shortest sequences
        dfs(res, beginWord, endWord, new ArrayList(), children, distance);
        return res;
    }
    
    public void dfs(List<List<String>> res, String beginWord, String endWord, List<String> currList, Map<String, List<String>> children, Map<String, Integer> distance) {
        currList.add(beginWord);
        if (beginWord.equals(endWord)) {
            res.add(new ArrayList(currList));
        }
        else {
            for (String currWord : children.get(beginWord)) {
                // 如果这个neighbor是最短的path上的
                if (distance.get(currWord) == (distance.get(beginWord) + 1)) {
                    dfs(res, currWord, endWord, currList, children, distance);
                }
            }
        }
        currList.remove(currList.size()-1);
    }
    
}
