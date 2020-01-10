class Solution {
    // Appraoch 1 - Brute Force
    // what came to my mind at first thought would be to merge two sorted arrays first
    // and then find the median of the sorted array
    // time - O(min(m, n)) space - O(m + n)
    
    // Approach 2 - Binary Search
    // Partition two arrays into four parts, the number of two left parts should be smaller than or equal to the number of two right parts
    // #left1 + #left2 = (#1 + #2 + 1) / 2
    // consider both odd number and even number case
    // !!! always search the smaller one
    // time - O(log(min(m, n))) space - O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        
        // always search in smaller one
        if (n1 > n2)
            return findMedianSortedArrays(nums2, nums1);
        
        // restult
        double mid = 0;
        
        // binary search pointers
        int left = 0;
        int right = n1;
        // <= 里的 = 是考虑到数组长度为1的情况
        while (left <= right) {
            // here "+1" because we want mid is the number of left part
            int mid1 = (right - left) / 2 + left;
            int mid2 = (n1 + n2 + 1) / 2 - mid1;
            
            // if left or right part of one of array is empty
            int left1 = mid1 <= 0 ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int left2 = mid2 <= 0 ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int right1 = mid1 >= n1 ? Integer.MAX_VALUE : nums1[mid1];
            int right2 = mid2 >= n2 ? Integer.MAX_VALUE : nums2[mid2];
            
            int leftmax = Math.max(left1, left2);
            int rightmin = Math.min(right1, right2);
            
            if (left1 <= right2 && left2 <= right1) {
                mid = (n1 + n2) % 2 == 0 ? (leftmax + rightmin) / 2.0 : leftmax;
                return mid;
            }
            else if (left1 > right2)
                right = mid1 - 1;
            else
                left = mid1 + 1;
        }
        return mid;
    }
}
