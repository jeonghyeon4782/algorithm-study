import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_7579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int mArr[] = new int[N];
        int cArr[] = new int[N];
        st = new StringTokenizer(br.readLine());
        StringTokenizer st1 = new StringTokenizer(br.readLine());

        int sum = 0;

        for (int i = 0; i < N; i++) {
            mArr[i] = Integer.parseInt(st.nextToken());
            cArr[i] = Integer.parseInt(st1.nextToken());
            sum += cArr[i];
        }

        int[] dp = new int[sum + 1];

        // 무료 앱 먼저 반영
        for (int i = 0; i < N; i++) {
            if (cArr[i] == 0) {
                dp[0] += mArr[i];
            }
        }

        // 나머지 앱으로 배낭 DP
        for (int i = 0; i < N; i++) {
            if (cArr[i] == 0) continue; // 이미 처리된 무료 앱 건너뛰기
            int m = mArr[i];
            int c = cArr[i];
            for (int j = sum; j >= c; j--) {
                dp[j] = Math.max(dp[j], dp[j - c] + m);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= sum; i++) {
            if (dp[i] >= M) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);

    }
}
