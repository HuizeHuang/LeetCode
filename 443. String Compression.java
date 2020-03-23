class Solution {
    // Approach 1 - HashMap
    // time: O(n)
    // space: O(n)
    
    // Approach 2 - three pointers
    // time: O()
    // space: O()
    public int compress1(char[] chars) {
        int write = 0, start = 0;
        for(int read = 0; read < chars.length; read++) {
            if ((read == chars.length - 1 || chars[start] != chars[read + 1])) {
                chars[write++] = chars[read];
                // write only when length > 2
                if (read > start){
                    String cnt = "" + (read - start + 1);
                    for (char c : cnt.toCharArray())
                        chars[write++] = c;
                }
                start = read + 1;
            }
        }
        return write;
    }
    
    // Approach 3 - two pointers
    public int compress(char[] chars) {
        int write = 0, curr = 0;
        while (curr < chars.length) {
            
            char currChar = chars[curr];
            int count = 0;
            while (curr < chars.length && chars[curr] == currChar) {
                curr++;
                count++;
            }
            chars[write] = currChar;
            if (count != 1) {
                for (char c : (count+"").toCharArray())
                    chars[++write] = c;
            }
            write++;
        }
        return write;
    }
}
