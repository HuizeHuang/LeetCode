class Solution1 {
    public String countAndSay(int n) {
        //recursion: 用n-1来求n
        if(n == 1) 
            return "1";
        String last = countAndSay(n-1);
        return getNextString(last);
    }
    public String getNextString(String last){
        if(last.length() == 0)
            return "";
        int num = getRepeatNum(last);
        return num + "" + last.charAt(0) + getNextString(last.substring(num));
    }
    
    public int getRepeatNum(String last){
        int num = 0;
        char first = last.charAt(0);
        for(int i = 0; i < last.length(); i++){
            if(last.charAt(i) != first) break;
            num++;
        }
        return num;
    }
}


class Solution {
    // Approach 1 - Recursive
    // for iterative, adding a while loop outside
    // time: O(2^n)
    // space: O(2^n)
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String s = countAndSay(n - 1);
        
        String res = "";
        for(int i = 0; i < s.length(); i++) {
            int count = 1;
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                count++;
                i++;
            }
            res += count + "" + s.charAt(i);
        }
        return res;
    }
}
