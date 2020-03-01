class Solution {
    // Approach 1 - dfs - stack overflow
    // time: O(2^(tx+ty)), a loose bound found by considering every move as 
    // (x, y) -> (x+1, y) or (x, y) -> (x, y+1) instead.
    // space: O(tx * ty) the size of the implicit call stack.
    public boolean reachingPoints1(int sx, int sy, int tx, int ty) {
        if (sx == tx && sy == ty) return true;
        else if (sx > tx || sy > ty) return false;
        return reachingPoints(sx + sy, sy, tx, ty) || reachingPoints(sx, sx + sy, tx, ty);
    }
    
    // Approach 2 - dfs + memo - memory limit exceeds
    // time: O(tx * ty) 
    // space: O(tx * ty)
    public boolean reachingPoints2(int sx, int sy, int tx, int ty) {
        Boolean[][] memo = new Boolean[tx+1][ty+1];
        return dfs(sx, sy, tx, ty, memo);
    }
    
    public Boolean dfs(int sx, int sy, int tx, int ty, Boolean[][] memo) {
        if (sx == tx && sy == ty) return true;
        if (sx > tx || sy > ty) return false;
        if (memo[sx][sy] != null) return memo[sx][sy];
        
        return memo[sx][sy] = (dfs(sx + sy, sy, tx, ty, memo) || dfs(sx, sx + sy, tx, ty, memo));
    }
    
    // Approach 3 - working backwards - TLE
    // transform the target point to the starting point via applying the parent operation (x, y) -> (x-y, y) or (x, y-x)
    // time: O(max(tx, ty))
    // space: O(1)
    public boolean reachingPoints3(int sx, int sy, int tx, int ty) {
        while (tx >= sx && ty >= sy) {
            if (tx == sx && ty == sy)
                return true;
            if (tx > ty) tx -= ty;
            else ty -= tx;
        }
        return false;
    }
    
    // Approach 3 - working backwards - modulo
    // replacing tx -= ty with tx = tx % ty
    /*
    for tx == ty
    tx = ty implies that (tx-ty, ty) = (ty, ty) or (tx, tx-ty) = (tx, tx) 
    or that they are the starting points
    which implies that tx = 0 or ty = 0
    here the problem states that sx and sy > 0, so it's not possible
    */
    // time: O(log(max(tx, ty)))
    // space: O(1)
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (sx <= tx && sy <= ty) {
            if (tx == ty) break;
            else if (tx > ty) {
                if (ty > sy) 
                    tx = tx % ty;
                else // ty <= sy
                    return (tx - sx) % ty == 0;
            }
            else { // tx < ty
                if (tx > sx)
                    ty = ty % tx;
                else
                    // different from ty % tx == sy
                    return (ty - sy) % tx == 0;
            }
        }
        return (tx == sx && ty == sy);
    }
}
