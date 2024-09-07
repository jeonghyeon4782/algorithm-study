import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1915 {

    static int[][] dp;
    static int N, M, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                dp[i][j] = Integer.parseInt(input[j]);
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (dp[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                result = Math.max(result, dp[i][j]);
            }
        }

        System.out.println((int)Math.pow(result, 2));
    }
}
