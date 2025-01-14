import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1253 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;
        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        for (int idx = 0; idx < N; idx++) {
            int s = 0;
            int e = N - 1;
            while (s < e) {
                if (s == idx) {
                    ++s;
                    continue;
                }
                if (e == idx) {
                    --e;
                    continue;
                }
                long sum = (long)nums[s] + nums[e];
                if (sum == nums[idx]) {
                    ++cnt;
                    break;
                } else if (sum > nums[idx]) --e;
                else ++s;
            }
        }
        System.out.println(cnt);
    }
}