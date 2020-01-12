class Solution {
    // Approach 1 - Stack
    // Two stacks, one to keep track of number, one to keep track of string outside the current bracket
    // time space
    public String decodeString(String s) {
        Deque<Integer> intStack = new ArrayDeque();
        Deque<String> strStack = new ArrayDeque();
        // either use string or use stringbuilder
        String currStr = "";
        int currNum = 0;
        for (char c : s.toCharArray()) {
 
            // number
            if (Character.isDigit(c)) {
                // if the number is 23[]
                currNum = currNum * 10 + (c - '0');
            }
            else if (c == '[') {
                intStack.push(currNum);
                // push current temp string first
                strStack.push(currStr);
                // remember to reset the value
                currStr = "";
                currNum = 0;
            }
            else if (c == ']') {
                // once meet the ending bracket, pop the stored num and string
                // repeat current string with num and append to poped string
                int num = intStack.pop();
                String temp = strStack.pop();
                
                // repeat for current string
                for (int i = 0; i < num; i++) {
                    temp += currStr;
                }
                currStr = temp;
            }
            else {
                currStr += c;
            }
        }
        return currStr;
    }
}
