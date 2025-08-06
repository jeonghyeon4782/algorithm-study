class PG_12907 {
    public int solution(int n, int[] money) {
        int answer = 0;
        int[][] dp = new int[money.length + 1][n + 1];

        for (int i = 0; i <= money.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= money.length; i++) {
            for (int j = 1; j <= n; j++) {
                if (j - money[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - money[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[money.length][n];
    }
}