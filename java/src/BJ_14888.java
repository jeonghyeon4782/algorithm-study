import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14888 {
    static int N;
    static int[] nums;
    static int[] op;
    static int minNum = Integer.MAX_VALUE;
    static int maxNum = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        op = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        dfs(0, nums[0]);
        System.out.println(maxNum);
        System.out.println(minNum);
    }

    private static void dfs(int cnt, int num) {
        if (cnt == N - 1) {
            minNum = Math.min(minNum, num);
            maxNum = Math.max(maxNum, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] == 0) continue;
            op[i]--;
            int newNum = cul(i, num, nums[cnt + 1]);
            dfs(cnt + 1, newNum);
            op[i]++;
        }
    }

    private static int cul(int op, int num1, int num2) {
        switch (op) {
            case 0:
                return num1 + num2;
            case 1:
                return num1 - num2;
            case 2:
                return num1 * num2;
            default:
                if (num1 < 0) {
                    num1 = -num1;
                    return -(num1 / num2);
                }
                return num1 / num2;
        }
    }
}
