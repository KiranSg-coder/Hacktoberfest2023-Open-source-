public class SubsetSum {
    //---------------------------------------------------------
    // **MEMOIZATION**
    //---------------------------------------------------------
    // TC: O(n * sum) where n is the number of elements in 'a' and 'sum' is the target sum.
    // SC: O(n * sum) for the memoization table 'dp'.
    //---------------------------------------------------------
    static boolean f(int ind, int sum, int arr[],  Boolean[][] dp) {
        if (sum == 0) return true;
        if (ind == 0) return (arr[0] == sum);

        if (dp[ind][sum] != null) return dp[ind][sum];

        boolean notTake = f(ind - 1, sum, arr, dp);
        boolean take = false;
        if (arr[ind] <= sum) take = f(ind - 1, sum - arr[ind], arr, dp);
        return dp[ind][sum] = take || notTake;
    }

    //---------------------------------------------------------
    // **TABULATION**
    //---------------------------------------------------------
    // TC: O(n * sum) where n is the number of elements in 'a' and 'sum' is the target sum.
    // SC: O(n * sum) for the tabulation table 'dp'.
    //---------------------------------------------------------
    // static boolean subsetSum(int a[], int sum) {
    //     int n = a.length;
    //     boolean[][] dp = new boolean[n][sum + 1];
    //     dp[0][0] = true;
    //     if (a[0] <= sum) {
    //         dp[0][a[0]] = true;     
    //     }
    //     for (int ind = 1; ind < n; ind++) {
    //         for (int target = 1; target <= sum; target++) {
    //             boolean notTake = dp[ind - 1][target];
    //             boolean take = false;
    //             if (a[ind] <= target) {
    //                 take = dp[ind - 1][target - a[ind]];
    //             }
    //             dp[ind][target] = take || notTake;
    //         }
    //     }
    //     return dp[n - 1][sum];
    // }

    //---------------------------------------------------------
    // **SPACE OPTIMIZATION (Tabulation with Space Optimization)**
    //---------------------------------------------------------
    // TC: O(n * sum) where n is the number of elements in 'a' and 'sum' is the target sum.
    // SC: O(sum) for the 'prev' and 'cur' arrays.
    //---------------------------------------------------------
    static boolean subsetSum(int a[], int sum) {
        int n = a.length;
        boolean[] prev = new boolean[sum + 1];
        prev[0] = true;
        if (a[0] <= sum) {
            prev[a[0]] = true;     
        }
        for (int ind = 1; ind < n; ind++) {
            boolean[] cur = new boolean[sum + 1];
            cur[0] = true;
            for (int target = 1; target <= sum; target++) {
                boolean notTake = prev[target];
                boolean take = false;
                if (a[ind] <= target) {
                    take = prev[target - a[ind]];
                }
                cur[target] = take || notTake;
            }
            prev = cur;
        }
        return prev[sum];
    }
}
