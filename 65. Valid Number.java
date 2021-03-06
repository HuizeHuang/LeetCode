class Solution {
    public boolean isNumber(String s) {
        boolean numberSeen = false;
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberAfterE = true;
        
        s = s.trim();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9'){
                numberSeen = true;
                numberAfterE = true;
            }
            else if (c == '.') {
                if (pointSeen || eSeen)
                    return false;
                pointSeen = true;
            }
            else if (c == 'e') {
                if (eSeen || !numberSeen)
                    return false;
                eSeen = true;
                numberAfterE = false;
            }
            else if (c == '+' || c == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e')
                    return false;
            }
            else {
                return false;
            }
        }
        return numberAfterE && numberSeen;
        
    }
}
