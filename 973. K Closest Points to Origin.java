class Solution {
    // Approach 1 - Sorting
    // time: O(nlogn)
    // space: O(1)
    public int[][] kClosest1(int[][] points, int K) {
        Arrays.sort(points, (a, b) -> (a[0]*a[0]+a[1]*a[1]-b[0]*b[0]-b[1]*b[1]));
        return Arrays.copyOfRange(points, 0, K);
    }
    
    // Approach 2 - Priority Queue - max heap
    // time: O(n * logk)
    // space: O(k)
    public int[][] kClosest2(int[][] points, int K) {
        if (points.length == 0) return new int[1][0];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>((b, a) -> (a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1]));
        
        int[][] res = new int[K][2];
        for (int i = 0; i < points.length; i++) {
            maxHeap.add(points[i]);
            if (maxHeap.size() > K) 
                maxHeap.poll();
        }
        int i = 0;
        while (!maxHeap.isEmpty()) {
            res[i++] = maxHeap.poll();
        }
        return res;                                           
    }
    
    // Approach 3 - Quick select
    public int[][] kClosest(int[][] points, int K) {
        int l = 0, r = points.length - 1;
        while(l <= r) {
            int pivot = quickSort(points, l, r);
            if (pivot == K) break;
            else if (pivot  < K)
                l = pivot + 1;
            else
                r = pivot - 1;
        }
        return Arrays.copyOfRange(points, 0, K);
    }
    
    public int quickSort(int[][] points, int l, int r) {
        int[] pivot = points[l];
        while(l < r) {
            while(l < r && compare(points[r], pivot) >= 0)
                r--;
            points[l] = points[r];
            while(l < r && compare(points[l], pivot) <= 0)
                l++;
            points[r] = points[l];
            
        }
        points[l] = pivot;
        return l;
    }

    public int compare(int[] a, int[] b) {
        return a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1];
    }
}
