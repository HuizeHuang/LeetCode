class Solution {
    
    // Approach 1 - Brute Force
    // time - O(n ^ 2) space O(1)
    public int trap(int[] height) {
        int totalWater = 0;
        // 从当前位置，向两边扩散找过去，找到两边最高的bar中较小的值，算出当前积水体积
        // 写while的时候一定记得前进一步
        for (int i = 1; i < height.length - 1; i++) {
            int left = i - 1, right = i + 1;
            int leftMax = -1, rightMax = -1;
            
            while (left >= 0) 
                leftMax = Math.max(height[left--], leftMax);
            
            while (right < height.length)
                rightMax = Math.max(height[right++], rightMax);
            
            int minHeight = Math.min(leftMax, rightMax);
            if (minHeight > height[i])
                totalWater += minHeight - height[i];
        }
        return totalWater;
    }
    
    
    // Approach 2 - DP - 3 pass
    // 两个数组 分别记录下从左边走和从右边走到每一点目前为止最大的高度
    // time O(3n) ~ O(n) space O(2n)
    public int trap(int[] height) {
        int n = height.length;
        int totalWater = 0;
        if (height == null || n == 0) return totalWater;
        
        int fromLeft[] = new int[n];
        fromLeft[0] = height[0];
        for (int i = 1; i < n; i++)
            fromLeft[i] = Math.max(height[i], fromLeft[i - 1]);
        
        int fromRight[] = new int[n];
        fromRight[n - 1] = height[n - 1];
        for (int j = n - 2; j >= 0; j--)
            fromRight[j] = Math.max(height[j], fromRight[j + 1]);
        
        for (int i = 0; i < n; i++)
            totalWater += Math.min(fromLeft[i], fromRight[i]) - height[i];
        
        return totalWater;
    }
    
    
    // Approach 3 - Using stacks
    // stack从大到小往上存储，栈顶永远是当前的最小值，
    // 一旦遇到比顶点大的值，则说明有一个bound, 栈顶的下一个永远比栈顶大
    // time O(2n) ~ O(n) space O(n)
    public int trap(int[] height) {
        int totalWater = 0;
        if (height == null || height.length == 0) return totalWater;
        int n = height.length;
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++){
            
            while (!stack.isEmpty() && height[i] > height[stack.peek()]){
                int top = stack.pop();
                if (stack.isEmpty()) break;  // 每次pop结束之后都记得检查stack是否为空
                int leftBound = stack.peek();
                int dist = i - leftBound - 1;
                int bounded_height = Math.min(height[i], height[leftBound]) - height[top];
                totalWater += dist * bounded_height;
            }
            stack.push(i);
        }
        return totalWater;
    }
    
    
    // Approach 4 - Using two pointers 
    // 左右两个指针，如果左指针的值大于右边的，说明右指针的左边肯定有一个bound
    // 则向左移动右指针，遇到比当前右边最大值小的则说明有trapping water
    // time O(n) space O(1)
    public int trap(int[] height) {
        int totalWater = 0;
        if (height == null || height.length == 0) return totalWater;
        int n = height.length;
        
        int left = 0, right = n - 1;
        int leftMax = -1, rightMax = -1;
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] < leftMax) 
                    totalWater += leftMax - height[left];
                else
                    leftMax = height[left];
                left++;
            }
            
            else{   // h_left >= h_right
                if (height[right] < rightMax) 
                    totalWater += rightMax - height[right];
                else
                    rightMax = height[right];
                right--;
            }
        }
        return totalWater;
    }
}
