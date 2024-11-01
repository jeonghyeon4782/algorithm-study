import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2343 {

    static int N, M;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int s = 0;
        int e = 0;
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            s = Math.max(s, nums[i]);
            e += nums[i];
        }
        binarySearch(s, e);
    }

    public static void binarySearch(int s, int e) {
        while (s <= e) {

            int mid = (s + e) / 2;

            if (bluerayCnt(mid) == M) {
                System.out.println(mid);
                return;
            } else if (bluerayCnt(mid) < M) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
    }

    private static int bluerayCnt(int mid) {
        int cnt = 0;
        int sum = 0;

        for (int i = 0; i < N - 1; i++) {
            sum += nums[i];
            if (sum + nums[i + 1] > mid) {
                cnt++;
                sum = 0;
            }
        }
        return cnt + 1;
    }
}
