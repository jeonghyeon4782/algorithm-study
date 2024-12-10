import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_15989 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int[] dp = new int[10001];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 3;
            dp[4] = 4;
            int target = Integer.parseInt(br.readLine());
            if (dp[target] != 0) {
                System.out.println(dp[target]);
                continue;
            }
            for (int j = 5; j <= target; j++) {
                if (j % 2 == 0 || j % 3 == 0) {
                    dp[j] = dp[j - 1] + 2;
                } else {
                    dp[j] = dp[j - 1] + 1;
                }
            }
            System.out.println(dp[target]);
        }
    }
}
