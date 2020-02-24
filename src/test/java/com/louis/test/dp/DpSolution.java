package com.louis.test.dp;

/**
 * @author Louis
 */
public class DpSolution {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length <= 0 || obstacleGrid[0].length <= 0) return 0;
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else if (i == 0 && j == 0) dp[i][j] = 1;
                else if (i == 0) dp[i][j] = dp[i][j - 1];
                else if (j == 0) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) dp[i][j] = 1;
                else dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}
