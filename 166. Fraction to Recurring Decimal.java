class Solution {
    // Math - long division
    // store all remainders and quotient into a hash table
    // edge case: 1, 6; 22, 7; 0, 3; -50, 8, -22, -2; -1, -2147483648
    
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";
        if (denominator == 0) return "";
        
        StringBuilder res = new StringBuilder();
        
        // if either one is negative, not both
        if (numerator < 0 ^ denominator < 0)
            res.append('-');
        
        // Convert to Long or else abs(-2147483648) overflows
        long remainder = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        
        // if result is integer, return right away
        res.append(remainder / divisor);
        remainder = remainder % divisor;
        if (remainder == 0)
            return res.toString();
            
        // from now on, it must be a fraction
        res.append('.');
        
        // remainder, quotient
        Map<Long, Integer> map = new HashMap();
        
        while (remainder != 0) {
            
            // if repeated
            if (map.containsKey(remainder)) {
                res.insert(map.get(remainder), "(");
                res.append(')');
                break;
            }
            // put remainder and quotient index into the map
            map.put(remainder, res.length());
            remainder *= 10;
            
            res.append(remainder / divisor);
            remainder = remainder % divisor;
            
        }
        return res.toString();
    }
}
