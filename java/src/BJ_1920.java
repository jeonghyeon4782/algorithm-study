import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Arrays.sort(nums);
        for (int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());
            int s = 0;
            int e = N - 1;
            boolean flag = false;
            while (s <= e) {
                int m = (s + e) / 2;
                if (nums[m] == target) {
                    flag = true;
                    break;
                } else if (nums[m] < target) {
                    s = m + 1;
                } else {
                    e = m - 1;
                }
            }
            if (flag) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }
        System.out.println(sb);
    }
}
