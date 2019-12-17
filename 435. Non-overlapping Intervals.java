class Solution {
    
    // Approach 1 - sort by start
    // Similar to 56.Merge Intervals
    // Sort based on the starting point
    // 注意是找到minimum移除的数量
    // time O(n logn)
    public int eraseOverlapIntervals1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        // sort by start number
        Arrays.sort(intervals, (a,b)->(a[0]-b[0]));
        
        int end = intervals[0][1];
        int start = intervals[0][0];
        int num = 0;
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {        // if overlapped
                num++;
                // !!!!
                end = Math.min(end, intervals[i][1]);
            }
            else{                               // if disjoint
                end = intervals[i][1];
                start = intervals[i][0];
            }
        }
        return num;
    }
    
    // Approach 2 - sort by end
    // get the maximum number of non-overlapping intervals
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        
        Arrays.sort(intervals, (a,b)->(a[1]-b[1]));
        
        int end = intervals[0][1];
        int count = 1;
        
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end){
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }
}
