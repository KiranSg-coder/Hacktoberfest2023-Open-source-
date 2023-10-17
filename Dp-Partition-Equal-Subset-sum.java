class Solution {
    // TC: O(N * sum), SC: O(sum)
    static boolean subsetSum(int a[], int sum) {
        int n = a.length;
        boolean[] prev = new boolean[sum + 1];
        prev[0] = true;
        
        // Initialize the first row of the DP table
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
            
            // Update the previous row for the next iteration
            prev = cur;
        }
        
        return prev[sum];
    }
    
    // TC: O(N * sum), SC: O(sum)
    static int equalPartition(int N, int arr[]) {
        int totSum = 0;
        
        // Calculate the total sum of the elements
        for (int i = 0; i < N; i++) {
            totSum += arr[i];
        }
        
        // If the total sum is odd, equal partition is not possible
        if (totSum % 2 != 0) {
            return 0;
        }
        
        int target = totSum / 2;
        
        // Check if there exists a subset with sum equal to 'target'
        return subsetSum(arr, target) ? 1 : 0;
    }
}
