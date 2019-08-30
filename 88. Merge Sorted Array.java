class Solution {
    // Approach 1 - Brute Force
    // time O(m * n) space O(1)
    
    // Approach 2 - start from the end 从后往前比较！！
    // time O(m) or O(n) space O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0){
            if (nums1[i] < nums2[j])
                nums1[k--] = nums2[j--];
            else
                nums1[k--] = nums1[i--];
        }
        while (j >= 0)
            nums1[k--] = nums2[j--];
    }
}
