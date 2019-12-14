class Solution {
    // If two words have the same frequency, then the word with the lower alphabetical order comes first.
    
    // Approach 1 - Heap (PQ)
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap();
        for (String word : words) 
            map.put(word, map.getOrDefault(word, 0) + 1);
        
        // minHeap, we can also use maxHeap here
        // 如果两个单词出现频率相同，要按照字母顺序插入
        PriorityQueue<String> minHeap = new PriorityQueue<String>((a,b) -> map.get(a) == map.get(b) ? b.compareTo(a) : map.get(a)-map.get(b));
        
        for (String key : map.keySet()) {
            minHeap.add(key);
            if (minHeap.size() > k)
                minHeap.poll();
        }
        
        List<String> res = new ArrayList();
        while(!minHeap.isEmpty())
            res.add(minHeap.poll());
        
        // Remeber to reverse the answer!!!
        Collections.reverse(res);
        
        return res;
    }
}
