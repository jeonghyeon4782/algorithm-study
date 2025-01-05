import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_22251 {

    static int[][] changeArr = {
            {0, 4, 3, 3, 4, 3, 2, 3, 1, 2},
            {4, 0, 5, 3, 2, 5, 6, 1, 5, 4},
            {3, 5, 0, 2, 5, 4, 3, 4, 2, 3},
            {3, 3, 2, 0, 3, 2, 3, 2, 2, 1},
            {4, 2, 5, 3, 0, 3, 4, 3, 3, 2},
            {3, 5, 4, 2, 3, 0, 1, 4, 2, 1},
            {2, 6, 3, 3, 4, 1, 0, 5, 1, 2},
            {3, 1, 4, 2, 3, 4, 5, 0, 4, 3},
            {1, 5, 2, 2, 3, 2, 1, 4, 0, 1},
            {2, 4, 3, 1, 2, 1, 2, 3, 1, 0}
    };
    static int[] num, targetNum;

    static int N, K, P, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        int answer = 0;
        num = new int[K];
        String s = String.valueOf(X);
        int idx = K - 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            num[idx--] = s.charAt(i) - '0';
        }
        targetNum = new int[K];

        for (int i = 1; i <= N; i++) {
            int sum = 0;
            if (i == X) continue;
            idx = K - 1;
            s = String.valueOf(i);
            for (int j = s.length() - 1; j >= 0; j--) {
                targetNum[idx--] = s.charAt(j) - '0';
            }
            for (int k = 0; k < K; k++) {
                sum += changeArr[num[k]][targetNum[k]];
            }
            if (sum <= P) {
                answer++;
            }
        }
        System.out.println(answer);
    }
}
