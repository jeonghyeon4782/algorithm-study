import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ST_6283 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[8];

        for (int i = 0; i < 8; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        String answer = null;
        int flag = 0;

        if (nums[0] == 1) {
            flag = 0;
        } else if (nums[0] == 8) {
            flag = 1;
        } else {
            flag = 2;
        }

        if (flag == 0) {
            for (int i = 0; i < 7; i++) {
                if (nums[i + 1] - nums[i] != 1) {
                    flag = 2;
                    break;
                }
            }
        } else if (flag == 1) {
            for (int i = 0; i < 7; i++) {
                if (nums[i] - nums[i + 1] != 1) {
                    flag = 2;
                    break;
                }
            }
        }
        if (flag == 0) {
            answer = "ascending";
        } else if (flag == 1) {
            answer = "descending";
        } else {
            answer = "mixed";
        }
        System.out.println(answer);
    }
}
