class Solution {
    // time O(n) space O(k)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (nums == null || n == 0) return nums;
        
        int[] res = new int[n - k + 1]; //注意数组的长度
        Deque<Integer> dq = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            //如果deque的第一个元素已经过了滑动窗口的最左边的值，则从队头移除
            if(!dq.isEmpty() && dq.peek() < i - k + 1)
                dq.poll();
            
            //如果数组当前的值要大于deque中最右边的值，则从队尾移除
            while (!dq.isEmpty() && nums[i] > nums[dq.peekLast()])
                dq.pollLast();
            
            //从队尾加入当前值
            dq.offer(i);
            
            //在结果中加入队头的值
            if (i - k + 1 >= 0)
                res[i - k + 1] = nums[dq.peek()];
        }
        return res;
    }
}
