import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1806 {
    static int N, S;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        int ans = Integer.MAX_VALUE;
        int s = 1;
        int e = 1;
        for (int i = 1; i <= N; i++) {
            int now = Integer.parseInt(st.nextToken());
            arr[i] = arr[i - 1] + now;
        }
        while (s <= e && e <= N) {
            int sum = arr[e] - arr[s - 1];
            if (sum >= S) {
                ans = Math.min(ans, e - s + 1);
                ++s;
            } else {
                ++e;
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
    }
}
