import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1943 {

    static class Coin {
        int value, cnt;

        public Coin(int value, int cnt) {
            this.value = value;
            this.cnt = cnt;
        }
    }

    static Coin[] coins;
    static int N, sumCoin;
    static boolean[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 0; t < 3; t++) {
            N = Integer.parseInt(br.readLine());
            coins = new Coin[N];
            sumCoin = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int value = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                sumCoin += (value * cnt);
                coins[i] = new Coin(value, cnt);
            }
            if (sumCoin % 2 != 0) {
                System.out.println(0);
                continue;
            }
            dp = new boolean[(sumCoin / 2) + 1];
            dp[0] = true;
            for (Coin coin : coins) {
                for (int cnt = 1; cnt <= coin.cnt; cnt++) {
                    for (int i = (sumCoin / 2); i >= coin.value; i--) {
                        if (dp[i - coin.value]) dp[i] = true;
                    }
                }
            }
            System.out.println(dp[sumCoin / 2] ? 1 : 0);
        }
    }
}
