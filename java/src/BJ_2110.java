import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int ans = 0;
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);
        int s = 1;
        int e = arr[N - 1] - arr[0];
        while(s <= e) {
            int m = (s + e) / 2;
            int cnt = 1;
            int start = arr[0];

            for (int i = 1; i < N; i++) {
                int d = arr[i] - start;
                if (d >= m) {
                    ++cnt;
                    start = arr[i];
                }
            }

            if (cnt >= C) {
                ans = m;
                s = m + 1;
            } else {
                e = m - 1;
            }
        }

        System.out.println(ans);
    }
}
