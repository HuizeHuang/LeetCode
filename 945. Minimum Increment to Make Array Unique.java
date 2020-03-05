class Solution {
    // Approach 1 - Array
    // 0 <= A.length <= 40000
    
    // Approach 2 - Math
    // use a variable that represents the value that is supposed to be if all values are unique
    public int minIncrementForUnique(int[] A) {
        if (A.length == 0) return 0;
        Arrays.sort(A);
        
        int supposed = A[0], res = 0;
        for (int num : A) {
            // if supposed < num, means there does not exist repeated number
            // if supposed > num, we need to add (s - num) to current value
            res += Math.max(0, supposed - num);
            supposed = Math.max(num + 1, supposed + 1);
        }
        return res;
    }
}
