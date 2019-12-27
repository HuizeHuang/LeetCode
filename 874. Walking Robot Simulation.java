class Solution {
    // Approach 1 
    // Use a Set data structure for the obstacles, so that we can check efficiently if our next step is obstructed.
    // greedy?
    public int robotSim(int[] commands, int[][] obstacles) {
        int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int maxDist = 0, direction = 0, x = 0, y = 0;
        
        // Store all obstacles coordinates as strings in a hashSet
        // We can also encode the obstacle using other formats to enhance the speed
        Set<String> set = new HashSet();
        for (int i = 0; i < obstacles.length; i++) {
            set.add(obstacles[i][0] + " " + obstacles[i][1]);
        }
        for (int command : commands) {
            if (command == -1) {        // turn right
                if (direction == 3)
                    direction = 0;
                else
                    direction++;
                
            }
            else if (command == -2) {   // turn left
                if (direction == 0)
                    direction = 3;
                else
                    direction--;
            }
            else {      // move forward

                while (command-- > 0 && !set.contains((x + directions[direction][0]) + " " + (y + directions[direction][1]))) {
                    x += directions[direction][0];
                    y += directions[direction][1];
                }
            }
            maxDist = Math.max(maxDist, x * x + y * y);
        }
        return maxDist;
    }
}
