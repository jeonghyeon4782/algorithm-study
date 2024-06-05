import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_4781 {

    static int n, m, c, p;
    static int[] calList, mountList, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken().split("\\.")[0]);
            if (n == 0 && m == 0) break;
            calList = new int[n];
            mountList = new int[n];
            dp = new int[m + 1];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                c = Integer.parseInt(st.nextToken());
                p = Integer.parseInt(st.nextToken().split("\\.")[0]);
                calList[i] = c;
                mountList[i] = p;
            }

            for (int i = 0; i < n; i++) {
                for (int j = mountList[i]; j < m + 1; j++) {
                    dp[j] = Math.max(dp[j], dp[j - mountList[i]] + calList[i]);
                }
            }
            System.out.println(dp[m]);
        }
    }
}
