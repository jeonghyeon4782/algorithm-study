import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] S = new long[n + 1]; // 합배열
        long[] C = new long[m];   // 나머지의 갯수
        long answer = 0;

        st = new StringTokenizer(br.readLine());
        // 합배열 만들기
        for (int i = 1; i <= n; i++) {
            long num = Long.parseLong(st.nextToken());
            S[i] = S[i - 1] + num;
        }
        for (int i = 1; i <= n; i++) {
            int remain = (int) (S[i] % m);
            if (remain == 0) answer++;
            C[remain]++;
        }

        for (int i = 0; i < m; i++) {
            if (C[i] > 1) {
                answer += C[i] * (C[i] - 1) / 2;
            }
        }

        System.out.println(answer);
    }
}
