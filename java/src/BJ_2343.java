import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2343 {
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int s = 0;
        int e = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            e += arr[i];
            s = Math.max(s, arr[i]);
        }
        int answer = 0;

        while (s <= e) {
            int m = (s + e) / 2;
            int cnt = solve(m);
            if (cnt <= M) {
                answer = m;
                e = m - 1;
            } else {
                s = m + 1;
            }
        }

        System.out.println(answer);
    }

    private static int solve(int m) {
        int cnt = 1;
        int sum = 0;

        for (int now : arr) {
            if (sum + now > m) {
                ++cnt;
                sum = now;
            } else {
                sum += now;
            }
        }

        return cnt;
    }
}
