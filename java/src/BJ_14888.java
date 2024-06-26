import java.util.Scanner;

public class BJ_14888 {

    static int[] op, nums;
    static int n;
    static int maxNum = Integer.MIN_VALUE;
    static int minNum = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        nums = new int[n];
        op = new int[4];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        for (int i = 0; i < 4; i++) {
            op[i] = sc.nextInt();
        }
        dfs(nums[0], 1);
        System.out.println(maxNum);
        System.out.println(minNum);
    }

    private static void dfs(int num, int idx) {
        if (idx == n) {
            maxNum = Math.max(maxNum, num);
            minNum = Math.min(minNum, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (op[i] <= 0) continue;
            op[i]--;
            switch (i) {
                case 0:
                    dfs(num + nums[idx], idx + 1);
                    break;
                case 1:
                    dfs(num - nums[idx], idx + 1);
                    break;
                case 2:
                    dfs(num * nums[idx], idx + 1);
                    break;
                case 3:
                    dfs(num / nums[idx], idx + 1);
                    break;
            }
            op[i]++;
        }
    }
}
