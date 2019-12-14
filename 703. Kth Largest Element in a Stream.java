class KthLargest {

    PriorityQueue<Integer> pq ;
    int k;
    public KthLargest(int k, int[] nums) {
        this.pq = new PriorityQueue<Integer>((a,b)->(a-b));
        this.k = k;
        for (int num : nums) {
            this.pq.add(num);
            
            if (pq.size() > k)
                this.pq.poll();
        }
    }
    
    public int add(int val) {
        this.pq.add(val);
        if (pq.size() > this.k)
            pq.poll();
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
