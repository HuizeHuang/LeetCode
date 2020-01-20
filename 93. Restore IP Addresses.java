



class Solution {
    // Approach 1 - Backtracking (DFS)
    /*
    Goal:
    1. we have 4 slots
    2. building pointer reaches the end of the string
    
    Constraints
    1. substring has no leading zero (not zero but the first element is zero)
    2. substring can't be greater than 255
    
    Choices:
    each slot has 3 positions to choose from
    */
    // time 
    // space
    List<String> res = new ArrayList();
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() == 0) return new ArrayList();
        int[] currIP = new int[4];
        
        backtracking(currIP, s, 0, 0);
        return res;
    }
    
    public void backtracking(int[] currIP, String s, int count, int currStart) {
        // goals: ending condition
        if (count == 4 && currStart == s.length()) {
            res.add(currIP[0] + "." + currIP[1] + "." + currIP[2] + "." + currIP[3]);
            return;
        }
        else if (count == 4 && currStart != s.length()){
            return;
        }
        
        // choices
        for (int i = 1; i <= 3 && currStart + i <= s.length(); i++) {
            String segment = s.substring(currStart, currStart + i);
            int value = Integer.parseInt(segment);
            
            // constraints
            // some edge cases: "010010"
            if ((segment.length() >= 2 && segment.charAt(0) == '0') || value > 255)
                break;
            
            // make the choice
            currIP[count] = value;
            // explore
            backtracking(currIP, s, count + 1, currStart + i);
            // undo the choice
            currIP[count] = -1;
        }
    }
}
 
