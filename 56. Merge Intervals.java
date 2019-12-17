class Solution {
    
    // Approach 1
    // quite the same as meeting room
    // 按起始时间排序，记录当前最晚结束时间，和下一个的起始时间挨个比较
    // time O(nlogn) space O(2n)
    public int[][] merge(int[][] intervals) {
        int length = intervals.length;
        List<int[]> res = new ArrayList();
        if (intervals == null || length == 0) return res.toArray(new int[0][]);
        
        Arrays.sort(intervals, (a, b) -> (a[0]-b[0]));
        
        int currStart = intervals[0][0];
        int currEnd = intervals[0][1];
        
        for (int[] interval : intervals) {
            
            if (interval[0] <= currEnd) {
                currEnd = Math.max(currEnd, interval[1]);
            }
            else{
                res.add(new int[] {currStart, currEnd});
                currStart = interval[0];
                currEnd = interval[1];
            }
        }
        res.add(new int[] {currStart, currEnd});
        return res.toArray(new int[res.size()][]);
    }
}
