import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_24337 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int s = a - 1;
        int e = n - b;
        int start = 0;
        int[] answer = new int[n];
        Arrays.fill(answer, 1);

        if (a + b - 1 > n) {
            System.out.println(-1);
            return;
        }

        start = Math.max(a, b);
        answer[e] = start;

        int cnt = 2;
        int idx = e - a + 2;
        int weight = 2;
        int max = 0;

        while (true) {
            if (idx >= e) break;
            answer[idx++] = weight++;
        }

        cnt = 1;
        idx = n - 1;
        weight = 1;

        while (true) {
            if (idx == e) break;
            answer[idx--] = weight++;
            if (++cnt >= b) {
                break;
            }
        }
//        10 3 5
//        10 1 9
//        10 1 8

        if (a == 1 && e > 0 && answer[e] > answer[e - 1]) {
            answer[0] = answer[e];
            answer[e] = 1;
        }

        for (int i : answer) {
            System.out.print(i + " ");
        }
    }
}
