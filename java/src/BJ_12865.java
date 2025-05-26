import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_12865 {

    static class Item {
        int w, v;
        public Item(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Item[] items = new Item[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            items[i] = new Item(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        int[][] dp = new int[K + 1][N + 1];
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int w = items[i].w;
            int v = items[i].v;
            for (int j = 1; j <= K; j++) {
                if (j < w) {
                    dp[j][i] = dp[j][i - 1];
                } else {
                    dp[j][i] = Math.max(dp[j][i - 1], dp[j - w][i - 1] + v);
                }
                answer = Math.max(answer, dp[j][i]);
            }
        }

        System.out.println(answer);
    }
}
