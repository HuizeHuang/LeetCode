class Solution {
    // Approach 1 - Brute Force
    // Sort the array by starting time and check the end time one by one
    // time O(n^2), space - O(n)
    
    // Approach 2 - minHeap
    // Sort the array by starting time 
    // put the interval into heap by comparing the ending time
    // On the condition that starting time is sorted, we want to see which one ends first.
    // time O(nlogn), space-O(n)
    public int minMeetingRooms1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        // Sort the array by starting time
        Arrays.sort(intervals, (a,b)->(a[0]-b[0]));
        
        // Add element to the heap
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((a,b)->(a-b));
        for (int i = 0; i < intervals.length; i++) {
            int currStart = intervals[i][0];
            if (minHeap.peek() == null || currStart < minHeap.peek())
                minHeap.add(intervals[i][1]);
            else {
                minHeap.poll();
                minHeap.add(intervals[i][1]);
            }
        }
        return minHeap.size();
    }
    
    // Approach 3 - Chronological ordering
    // 对所有开始时间和结束时间分开排序
    // 我们只想知道需要几个，并不关心是哪一间办公室被释放了
    // time O(2 * nlogn + 2*n) ~ O(nlogn) space O(2n)
    public int minMeetingRooms(int[][] intervals) {
        
        int res = 0;
        int length = intervals.length;
        if (intervals == null || length == 0) return res;
        
        int[] start = new int[length];
        int[] end = new int[length];
        
        for (int i = 0; i < length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        
        Arrays.sort(start);
        Arrays.sort(end);
        
        int e_ptr = 0;
        for (int s_ptr = 0; s_ptr < length; s_ptr++) {
            if (start[s_ptr] < end[e_ptr]) res++;
            else e_ptr++;
        }
        
        return res;
    }
}
