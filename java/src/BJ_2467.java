import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        int[] answer = new int[2];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0;
        int e = N - 1;

        while (s < e) {
            int a = nums[s];
            int b = nums[e];

            if (Math.abs(a + b) <= min) {
                answer[0] = a;
                answer[1] = b;
                min = Math.abs(a + b);
            }

            if (a + b <= 0) {
                ++s;
            } else {
                e--;
            }
        }
        System.out.println(answer[0] + " " + answer[1]);
    }
}
