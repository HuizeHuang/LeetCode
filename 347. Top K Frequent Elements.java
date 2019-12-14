class Solution {
    // Approach 1 - Bucket & hashmap
    // 3 loops
    // time O(k*l*n) ~ O(n) l is the number of unique number, k is top k, n is the number of elements, space O(n)
    public List<Integer> topKFrequent1(int[] nums, int k) {
        List<Integer> result = new ArrayList();
        Map<Integer, Integer> map = new HashMap();
        
        // Create the counting map
        for (int num : nums) {
            if (!map.containsKey(num)) map.put(num, 0);
            map.put(num, map.get(num) + 1);
        }
        
        // create an array of list representing the bucket, the length is nums length
        // the index being the frequency of unique numbers
        List<Integer>[] bucket = new List[nums.length + 1];
        for (int key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null)
                bucket[frequency] = new ArrayList();
            bucket[frequency].add(key);
        }
        
        //get the element from the end of list
        for (int i = bucket.length-1; i >=0 && result.size() < k; i--) {
            if (bucket[i] != null)
                result.addAll(bucket[i]);
        }
        return result;
    }
    
    // Approach 2 - maxHeap(Priority queue)
    // 有两种堆都可以用
    // 大顶堆比较容易，最后取前k个元素
    // 小顶堆可以维持堆的长度为k
    // time - O(nlog(n)) (insertion sort), space - O(n)
    public List<Integer> topKFrequent2(int[] nums, int k) {
        // Create the map counting frequency
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        // Create the minHeap/maxHeap
        // expression < 0 -> a在前
        PriorityQueue<Integer> maxHeap = new PriorityQueue((a, b)->(map.get(b)-map.get(a)));
        for (int key : map.keySet()) {
            maxHeap.add(key);
        }
        
        // Poll the first k elements
        List<Integer> res = new ArrayList();
        for (int i = 0; i < k; i++) {
            res.add(maxHeap.poll());
        }
        return res;
    }
    
    // Approach 3 - minHeap(Priority queue)
    // 小顶堆可以维持堆的长度为k
    // time - O(nlog(k))
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        // Create minHeap
        // expression < 0 -> a在前
        PriorityQueue<Integer> minHeap = new PriorityQueue((a,b)->(map.get(a)-map.get(b)));
        for (int key : map.keySet()) {
            minHeap.add(key);
            if (minHeap.size() > k) 
                minHeap.poll();
        }
        
        // Get all values in minHeap
        List<Integer> res = new ArrayList();
        while(!minHeap.isEmpty()) {
            res.add(minHeap.poll());
        }
        Collections.reverse(res);
        return res;
    } 
    
    // Approach 4 - TreeMap
    // Use the frequency as the key
    
}
