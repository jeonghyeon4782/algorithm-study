import java.util.Arrays;
import java.util.Scanner;

public class BJ_2631 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int maxCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxCnt = Math.max(maxCnt, dp[i]);
        }

        System.out.println(n - maxCnt);
    }
}
