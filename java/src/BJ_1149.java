import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BJ_1149 {
    static int N;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N + 1][3];
        int answer = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = sc.nextInt();
            }
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + dp[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + dp[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + dp[i][2];
        }

        for (int i = 0; i < 3; i++) {
            answer = Math.min(answer, dp[N][i]);
        }
        System.out.println(answer);
    }
}
