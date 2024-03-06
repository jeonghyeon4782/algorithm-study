import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1952 {
    static int[] costs;
    static int[] month;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            costs = new int[4];
            month = new int[12];
            dp = new int[12];
            int result = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                costs[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 12; i++) {
                month[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < 12; i++) {
                month[i] = Math.min(month[i] * costs[0], costs[1]);
            }
            dp[0] = month[0];
            dp[1] = month[1] + month[0];
            dp[2] = Math.min(month[0] + month[1] + month[2], costs[2]);
            for (int i = 3; i < 12; i++) {
                dp[i] = Math.min(dp[i - 1] + month[i], dp[i - 3] + costs[2]);
            }
            System.out.printf("#%d %d\n", test, Math.min(costs[3], dp[11]));
        }
    }
}
