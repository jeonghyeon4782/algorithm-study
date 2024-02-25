import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1182 {
    static int N, S, result;
    static boolean[] isSelected;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        isSelected = new boolean[N];
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        subset(0);
        System.out.println(S == 0 ? result -1 : result);
    }

    private static void subset(int cnt) {
        if (cnt == N) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                if (isSelected[i]) {
                    sum += nums[i];
                }
            }
            if (sum == S) result++;
            return;
        }

        isSelected[cnt] = true;
        subset(cnt + 1);
        isSelected[cnt] = false;
        subset(cnt + 1);
    }
}
