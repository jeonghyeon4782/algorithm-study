import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1253 {

    static int N, cnt;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);
        for (int i = 0; i < N; i++) {
            solve(i);
        }
        System.out.println(cnt);
    }

    public static void solve(int target) {
        int s = 0;
        int e = nums.length - 1;

        while (s < e) {
            if (nums[s] + nums[e] == nums[target]) {
                if (s == target) s++;
                else if (e == target) e--;
                else {
                    cnt++;
                    return;
                }
            } else if (nums[s] + nums[e] < nums[target]) {
                s++;
            } else {
                e--;
            }
        }
    }
}