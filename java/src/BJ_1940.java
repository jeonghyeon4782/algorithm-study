import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        int s = 0;
        int e = N - 1;
        int cnt = 0;

        while (s < e) {
            if (nums[s] + nums[e] == M) {
                --e;
                ++cnt;
            } else if (nums[s] + nums[e] < M) {
                ++s;
            } else {
                --e;
            }
        }

        System.out.println(cnt);
    }
}
