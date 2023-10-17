public class Solution {
    //---------------------------------------------------------
    // **RECURSIVE**
    //---------------------------------------------------------
    // TC: Exponential
    // SC: O(n) for the recursion stack.
    //---------------------------------------------------------
    private int recursiveRob(int ind, int[] nums) {
        if (ind < 0) return 0;
        int pick = nums[ind] + recursiveRob(ind - 2, nums);
        int notpick = recursiveRob(ind - 1, nums);
        return Math.max(pick, notpick);
    }
    
    public int robRecursive(int[] nums) {
        int n = nums.length;
        return recursiveRob(n - 1, nums);
    }
    
    //---------------------------------------------------------
    // **MEMOIZATION**
    //---------------------------------------------------------
    // TC: O(n)
    // SC: O(n) for the memoization table 'dp'.
    //---------------------------------------------------------
    private int memoizeRob(int ind, int[] nums, int[] dp) {
        if (ind < 0) return 0;
        if (dp[ind] != -1) return dp[ind];
        int pick = nums[ind] + memoizeRob(ind - 2, nums, dp);
        int notpick = memoizeRob(ind - 1, nums, dp);
        return dp[ind] = Math.max(pick, notpick);
    }
    
    public int robMemoization(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return memoizeRob(n - 1, nums, dp);
    }
    
    //---------------------------------------------------------
    // **TABULATION**
    //---------------------------------------------------------
    // TC: O(n)
    // SC: O(n) for the tabulation table 'dp'.
    //---------------------------------------------------------
    public int robTabulation(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        for (int i = 1; i < n; i++) {
            int pick = nums[i] + (i > 1 ? dp[i - 2] : 0);
            int notpick = dp[i - 1];
            dp[i] = Math.max(pick, notpick);
        }
        return dp[n - 1];
    }
    
    //---------------------------------------------------------
    // **SPACE OPTIMIZATION**
    //---------------------------------------------------------
    // TC: O(n)
    // SC: O(1)
    //---------------------------------------------------------
    public int robSpaceOptimized(int[] nums) {
        int n = nums.length;
        int prev = nums[0];
        int prev2 = 0;
        
        for (int i = 1; i < n; i++) {
            int pick = nums[i] + (i > 1 ? prev2 : 0);
            int notpick = prev;
            int current = Math.max(pick, notpick);
            prev2 = prev;
            prev = current;
        }
        
        return prev;
    }
}
