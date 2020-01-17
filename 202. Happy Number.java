class Solution {
    // How to detect a cycle?
    // Approach 1 - HashSet
    // time - O(logn) - the number of digits in a number is given by logn
    // space - O(logn)
    public boolean isHappy1(int n) {
        Set<Integer> set = new HashSet();
        
        while(n != 1) {
            int sum = 0;
            while (n > 0) {
                sum += (n % 10) * (n % 10); 
                n /= 10;
            }
            n = sum;
            if (!set.add(n))
                return false;
        }
        return true;
    }
    
    // Approach 2 - Floyd's Cycle-Finding Algorithm
    // two pointers, one moves fast and the other moves slow
    // time - O(logn) space - O(1)
    /*
    helper function to get next number (sum of sqaures)
    */
    public int getNext(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10) * (n % 10); 
            n /= 10;
        }
        return sum;
    }
    
    public boolean isHappy(int n) {
        int slow = n, fast = getNext(n);
        while (fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
            
        }
        return fast == 1;
    }
}
