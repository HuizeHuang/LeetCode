class Solution {
    
    // Approach 1 - two sets
    // 用 HashSet, it has O(1) time when doing operations like look up, in, contarin
    // 可以一边加入一边比较
    // time O(max(n,m))
    public int[] intersection1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return new int[0];
        
        Set<Integer> set = new HashSet();
        Set<Integer> intersection = new HashSet();
        
        for (int num : nums1) {
            set.add(num);
        }
        
        // look up numbers in the set on the run
        for (int num : nums2) {
            if (set.contains(num))
                intersection.add(num);
        }
        
        // hashset to array
        int[] res = new int[intersection.size()];
        int i = 0;
        for (Integer num : intersection)
            res[i++] = num;
        
        return res;
    }
    
    // Approach 2
    // 先排序，用两个指针比较两个数组里的元素，谁小就往前走一步
    // 用set来装结果，再转为array
    // time O（nlogn + min(n,m)) ~ O(nlogn)
    public int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return new int[0];
        
        // O(n logn)
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int p1=0, p2=0;
        Set<Integer> set = new HashSet();
        // O(min (n,m))
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2])  p1++;
            else if (nums1[p1] > nums2[p2]) p2++;
            else {
                set.add(nums1[p1]);
                p1++;
                p2++;
            }
        }
        
        int[] res = new int[set.size()];
        int i = 0;
        for (Integer num : set) 
            res[i++] = num;
        
        return res;
    }
    
    // Approach 3 - Binary Search
    // 和2类似，排序完查找用二分查找法
    // time O(nlogn + logn) ~ O(nlogn)
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return new int[0];
        
        Arrays.sort(nums1);
        
        Set<Integer> set = new HashSet();
        for (int num : nums2) {
            if (binarySearch(nums1, num))
                set.add(num);
        }
        
        int[] res = new int[set.size()];
        int i = 0;
        for (Integer num : set) {
            res[i++] = num;
        }
        
        return res;
    }
    
    public boolean binarySearch(int[] nums, int k) {
        int lo = 0;
        int hi = nums.length - 1;
        
        while (lo <= hi) {
            int mid = (hi - lo) / 2 + lo;
            
            if (nums[mid] == k)
                return true;
            else if (nums[mid] < k)
                lo = mid+1;
            else
                hi = mid-1;
        }
        return false;
    }
    
}
