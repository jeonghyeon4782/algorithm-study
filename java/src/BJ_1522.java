import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1522 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] nums;
        nums = br.readLine().toCharArray();
        int a = 0, b = 0;
        int inB = 0;
        int len = nums.length;

        for (char c : nums) {
            if (c == 'a') a++;
            else if (c == 'b') b++;
        }

        for (int i = 0; i < a; i++) {
            if (nums[i] == 'b') inB++;
        }

        if (b == 0 || a == 0 || a == 1) {
            System.out.println(0);
            return;
        }

        int s = 0;
        int e = a - 1;
        int answer = Integer.MAX_VALUE;

        while (s < len) {
            answer = Math.min(answer, inB);

            if (nums[s] == 'b') inB--;
            s++;
            e = (e + 1) % len;
            if (nums[e] == 'b') inB++;
        }

        System.out.println(answer);
    }
}
