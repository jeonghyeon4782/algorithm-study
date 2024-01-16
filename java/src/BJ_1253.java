import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int count = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums); // 오름차순 정렬

        for (int i = 2; i < n; i++) {
            int s = 0;
            int e = 1;
            int sumNum = 0;
            while (e < i) {
                sumNum = nums[s] + nums[e];
                if (sumNum < nums[i]) {
                    e++;
                }
                else if (sumNum == nums[i]) {
                    count++;
                    s++;
                }
                else {
                    s++;
                }
            }
        }
        System.out.println(count);
    }
}
