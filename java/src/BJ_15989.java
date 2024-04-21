import java.util.Scanner;

public class BJ_15989 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        int[] dp;
        for (int t = 0; t < test; t++) {
            int n = sc.nextInt();
            int idx = 3;
            int cnt = 0;
            dp = new int[10001];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 3;
            for (int i = 4; i <= n; i++) {
                ++cnt;
                dp[i] = dp[i - 3] + idx;
                if (cnt == 2) {
                    cnt = 0;
                    idx++;
                }
            }
            System.out.println(dp[n]);
        }
    }
}
