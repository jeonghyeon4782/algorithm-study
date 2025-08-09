import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[] dp = new int[T + 1];  // 시간당 최대 점수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());   // 예상 공부 시간
            int s = Integer.parseInt(st.nextToken());   // 문제의 배점
            for (int j = T; j >= k; j--) {
                dp[j] = Math.max(dp[j], dp[j - k] + s);
            }
        }

        System.out.println(dp[T]);
    }
}
