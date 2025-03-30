import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_13144 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(1);
            return;
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int s = 0;
        int e = 0;
        Set<Integer> set = new HashSet<>();
        set.add(nums[0]);

        while (true) {
            ++e;
            if (set.contains(nums[e])) {
                int key = nums[e];
                while (true) {
                    cnt += e - s;
                    set.remove(nums[s]);
                    if (nums[s++] == key) break;
                }
            }
            if (e == N - 1) {
                while (s <= e) {
                    cnt += (e - s) + 1;
                    s++;
                }
                break;
            }
            set.add(nums[e]);
        }
        System.out.println(cnt);
    }
}
