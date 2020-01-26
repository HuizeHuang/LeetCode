
class Solution {
    // Approach 1 - Sliding Window
    // Sliding Window of length 3 slides on array grumpy[]
    // how to optimize:
    // Say the store owner uses their power in minute 1 to X and we have some 
    // answer A. If they instead use their power from minute 2 to X+1, we only 
    // have to use data from minutes 1, 2, X and X+1 to update our answer A.
    // time: O(2 * n)
    // space: O(1)
    public int maxSatisfied1(int[] customers, int[] grumpy, int X) {
        int n = customers.length;
        int left = 0, right = X - 1;
        int curr = 0;
        for (int i = 0; i < n; i++) {
            if (i <= right)
                curr += customers[i];
            else
                curr += customers[i] * (grumpy[i] == 1 ? 0 : 1);
        }
        int res = curr;
        right++;
        while (right < n) {
            
            if (grumpy[right] == 1)
                curr += customers[right];
            if (grumpy[left] == 1)
                curr -= customers[left];
            right++;
            left++;
            if (curr > res)
                res = curr;
        }
        return res;
    }
    
    // optimize using one loop
    // res = totalCustomers - totalGrumpy + maxCurrSatisfied
    // time: O(n)
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int maxCurrSatisfied = 0, totalCustomers = 0, totalGrumpy = 0;
        int currSatisfied = 0;
        
        for (int i = 0; i < grumpy.length; i++) {
            totalCustomers += customers[i];
            totalGrumpy += customers[i] * grumpy[i];
            currSatisfied += customers[i] * grumpy[i];
            
            // keep the window size X
            if (i >= X) {
                currSatisfied -= customers[i - X] * grumpy[i - X];
            }
            if (maxCurrSatisfied < currSatisfied)
                maxCurrSatisfied = currSatisfied;
        }
        return totalCustomers - totalGrumpy + maxCurrSatisfied;
    }
}
 



