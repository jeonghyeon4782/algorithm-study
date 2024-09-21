import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BJ_14888 {
    static int N, maxNum, minNum;
    static int[] nums, operators;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        operators = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        maxNum = Integer.MIN_VALUE;
        minNum = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operators[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, nums[0]);
        System.out.println(maxNum);
        System.out.println(minNum);
    }

    private static void dfs(int depth, int num) {

        if (depth == N - 1) {
            minNum = Math.min(minNum, num);
            maxNum = Math.max(maxNum, num);
            return;
        }

        for (int i = 0; i < 4; i++) {

            if (operators[i] == 0) continue;
            operators[i]--;

            switch (i) {
                case 0:
                    dfs(depth + 1, num + nums[depth + 1]);
                    operators[i]++;
                    break;
                case 1:
                    dfs(depth + 1, num - nums[depth + 1]);
                    operators[i]++;
                    break;
                case 2:
                    dfs(depth + 1, num * nums[depth + 1]);
                    operators[i]++;
                    break;
                case 3:
                    dfs(depth + 1, num / nums[depth + 1]);
                    operators[i]++;
                    break;
            }
        }

    }
}
