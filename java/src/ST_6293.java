import java.io.*;
import java.util.*;

public class ST_6293 {

    static int[] dp, nums;
    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        dp = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        for (int i = 1; i < N; i++) {
            int maxCnt = 1;
            for (int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    maxCnt = Math.max(maxCnt, dp[j] + 1);
                }
            }
            dp[i] = maxCnt;
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}