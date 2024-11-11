import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Shortcut implements Comparable<Shortcut> {
    int s, e, d;

    public Shortcut(int s, int e, int d) {
        this.s = s;
        this.e = e;
        this.d = d;
    }

    public int compareTo(Shortcut o) {
        if (this.e != o.e) {
            return Integer.compare(this.e, o.e);
        } else {
            return Integer.compare(this.d, o.d);
        }
    }
}

public class BJ_1446 {
    static int N, D;
    static Shortcut[] shortcuts;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        shortcuts = new Shortcut[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            shortcuts[i] = new Shortcut(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(shortcuts);

        int[] dp = new int[D + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= D; i++) {
            for (Shortcut shortcut : shortcuts) {
                if (shortcut.e == i) {
                    dp[i] = Math.min(dp[shortcut.s] + shortcut.d, dp[i]);
                }
            }
            dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
        }

        System.out.println(dp[D]);
    }
}
