import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_12851 {

    static int N, K;
    static int resultTime, resultCnt;
    static int[] dp;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[100001];
        Arrays.fill(dp, Integer.MAX_VALUE);

        if (N == K) {
            System.out.println(0);
            System.out.println(1);
        } else {
            bfs(N);
            System.out.println(dp[K]);
            System.out.println(resultCnt);
        }
    }

    public static void bfs(int n) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        dp[n] = 0;

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == K) {
                resultCnt++;
            }

            if (isValid(now + 1) && dp[now + 1] >= dp[now] + 1) {
                q.offer(now + 1);
                dp[now + 1] = dp[now] + 1;
            }
            if (isValid(now - 1) && dp[now - 1] >= dp[now] + 1) {
                q.offer(now - 1);
                dp[now - 1] = dp[now] + 1;
            }
            if (isValid(now * 2) && dp[now * 2] >= dp[now] + 1) {
                q.offer(now * 2);
                dp[now * 2] = dp[now] + 1;
            }
        }

    }

    public static boolean isValid(int n) {
        return 0 <= n && n <= 100000;
    }
}
