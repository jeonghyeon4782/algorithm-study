import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1300 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        long s = 1;
        long e = K;
        long answer = 0;

        while (s <= e) {
            long m = (s + e) / 2;
            long cnt = 0;

            for (int i = 1; i <= N; i++) {
                cnt += Math.min(N, m / i);
            }

            if (cnt < K) {
                s = m + 1;
            } else {
                e = m - 1;
                answer = m;
            }
        }

        System.out.println(answer);
    }
}
