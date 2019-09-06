class Solution {
    /*
    // Approach 1 - Brute Force
    // time O(n^2) space O(1)
    // 如果当前值比后一位值要大的时候，从后往前遍历找到最大值，否则继续往前走
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (heights == null || n == 0) return 0;
        int maxArea = 0;
        
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || heights[i] > heights[i + 1]) {
                int current = i;
                int minHeight = heights[current];
                // 往回走
                while (current >= 0) {
                    int width = i - current + 1;
                    minHeight = Math.min(minHeight, heights[current]);
                    int area = width * minHeight;
                    maxArea = Math.max(maxArea, area);
                    current--;
                }
            }
        }
        return maxArea;
    }
    */
    
    // Approach 2 - Using stack
    // 找到左边和右边最小的边界
    // 栈里元素永远是递增的顺序，如果当前值比栈顶小，则为右边界
    // 栈里后面的元素都应为左边界，压出栈
    // https://www.youtube.com/watch?v=KkJrGxuQtYo
    // time O(n) space O(n)
    
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (heights == null || n == 0) return 0;
        if (n == 1) return heights[0];
        
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // the first index to subtract
        stack.push(0);
        int current, area, right, left;
        
        for (right = 1; right < n; right++) {    
            while (stack.peek() != -1 && heights[right] < heights[stack.peek()]) {
                current = heights[stack.pop()];
                left = stack.peek();
                area = current * (right - left - 1);
                maxArea = Math.max(maxArea, area);
            } 
            stack.push(right);
        }
        
        // 若数组最后是递增，则stack里面有剩余
        // 需要处理这些剩余
        int lastValid = 0;
        while (stack.peek() != -1) {
            current = heights[stack.pop()];
            left = stack.peek();
            area = current * (right - left - 1);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }
}
