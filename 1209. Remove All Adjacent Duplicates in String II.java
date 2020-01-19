class Solution {
    // Brute Force
    // Count repeating adjacent letters and remove them when count reaches k,
    // then we do it again from the start, until there is nothing to remove
    // time: O(n^2 / k)
    // space: O(n)
    
    // Approach 1 - Memoise count
    // Based on brute force solution, we observed that many letters are counted repeatedly,
    // maybe we can store the count in an array so that we don't have to start from the start
    // everytime we remove a substring
    // time - O(n)
    // space - O(n)
    public String removeDuplicates1(String s, int k) {
        if (s == null || s.length() == 0 || k > s.length()) return "";
        StringBuilder sb = new StringBuilder(s);
        int[] count = new int[s.length()];
        Arrays.fill(count, 1);
        
        for (int i = 1; i < sb.length(); i++) {
            if (i > 0 && sb.charAt(i) == sb.charAt(i - 1)) {
                count[i] = count[i - 1] + 1;
                if (count[i] == k) {
                    sb.delete(i - k + 1, i + 1);
                    // set i back to where it should start
                    i = i - k;  // we have i++ later
                }
            }
            // if not equal, remember to set to 1
            else {
                count[i] = 1;
            }
        }
        return sb.toString();
    }
    
    // Approach 2 - Memoise using stack
    // Instead of using array in approach 1, we can use stack to store the count
    // time - O(n)
    // space - O(n)
    
    // Approach 3 - stack + Pair / or two stacks (one for count, one for char)
    // We can directly store current character and their counts in the stack
    // If current is equal to previous character, we increment the count by one
    // if not, we keep pushing the new char with count 1 into the stack
    // time: O(n)
    // space: O(n)
    class Pair{
        char key;
        int value;
        public Pair(char key, int value){
            this.key = key;
            this.value = value;
        }
    }
    public String removeDuplicates(String s, int k) {
        if (s == null || s.length() == 0) return "";
        String res = "";
        
        Stack<Pair> stack = new Stack();
        for (char c : s.toCharArray()) {
            // if met
            if (!stack.isEmpty() && c == stack.peek().key) {
                Pair pair = stack.pop();
                pair.value = pair.value + 1;
                // if not reach k, push it back. otherwise leave it alone
                if (pair.value != k)
                    stack.push(pair);
            }
            // if not met
            else{
                stack.push(new Pair(c, 1));
            }
        }
        
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            char c = pair.key;
            int count = pair.value;
            String temp = "";
            while (count-- >= 1) {
                temp += c;
            }
            res = temp + res;
        }
        return res;
    }
    
    // Approach 4 - Two pointers
    // Come back later for this approach
}
