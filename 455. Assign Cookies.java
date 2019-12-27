class Solution {
    // Approach 1 - myself
    // To maximize the number of content children -> we start with the children
    // with the minimum greed
    // --> that needs us to sort the array first
    // faster than 16.87%
    // time - O(2*nlogn)
    public int findContentChildren1(int[] g, int[] s) {
        if (g == null || g.length == 0 || s == null || s.length == 0) 
            return 0;
        // sort the array to assign from the minimal greed
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        int count = 0;
        for (int greed : g) {
            while (i < s.length && s[i++] < greed) {
            }
            if (i == s.length) {
                if (s[i-1] >= greed)
                    count++;
                break;
            } 
            count++;
        }
        return count;
    }
    
    // Approach 2
    // time -  O(2*n*logn + min(|g|, |s|)), n is the larger length of two
    // space - O(1)
    public int findContentChildren(int[] g, int[] s) { 
        Arrays.sort(g);
        Arrays.sort(s);
        
        int i = 0, j = 0, count = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
                j++;
                count++;
            }
            else
                j++;
        }
        return count;
    }
}
