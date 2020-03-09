import java.math.BigInteger;
class Solution {
    /* Question to ask
    ** What is the longest possible length for a and b
    ** Integer: 32bit
    ** Long: 64 bit
    ** BigInteger: 2^(Integer.MAX_VALUE) where Integer.MAX_VALUE = 2^31-1
    */
    
    // Approach 1 - Similar to lc 2. Add Two Numbers
    // time: O(max(|a|, |b|))
    // space: O(max(|a|, |b|))
    public String addBinary1(String a, String b) {
        // we always asume that a has shorter length
        if (b.length() < a.length())
            return addBinary(b, a);
        int carry = 0, curr = 0;
        int i = a.length() - 1, j = b.length() - 1;
        StringBuilder res = new StringBuilder();

        while (i >= 0 || j >= 0) {
            int sum = 0;
            if (i >= 0) 
                sum += a.charAt(i--) - '0'; 

            if (j >= 0) 
                sum += b.charAt(j--) - '0';

            curr = (sum + carry) % 2;
            carry = (sum + carry) / 2;
            
            res.append(curr);

        } 
        if (carry > 0)
            res.append(carry);
        
        return res.reverse().toString();
    }
    
    // Approach 2 - bit manipulation
    // XOR - addtion
    // AND - carry
    // time: I think it is O(N+M) because of the time taken to convert a and b to ints.
    // space: O(max(|a|, |b|))
    public String addBinary(String a, String b) {
        BigInteger x = new BigInteger(a, 2);
        BigInteger y = new BigInteger(b, 2);
        
        BigInteger answer = x.xor(y);
        BigInteger carry = x.and(y).shiftLeft(1);
        BigInteger zero = new BigInteger("0", 2);
        
        while (!carry.equals(zero)) {
            answer = x.xor(y);
            carry = x.and(y).shiftLeft(1);
            x = answer;
            y = carry;
        }
        
        return answer.toString(2);
    }
}
