import java.util.Scanner;

public class BJ_15989 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int[][] dp = new int[n + 1][4];

            for (int j = 1; j <= n; j++) {
                if (j >= 1) dp[j][1] = 1;
                if (j >= 2) dp[j][2] = (j == 2) ? 1 : dp[j - 2][1] + dp[j - 2][2];
                if (j >= 3) dp[j][3] = (j == 3) ? 1 : dp[j - 3][1] + dp[j - 3][2] + dp[j - 3][3];
            }

            System.out.println(dp[n][1] + dp[n][2] + dp[n][3]);
        }
        sc.close();
    }
}
