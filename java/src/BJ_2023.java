import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ_2023 {
    static ArrayList<Integer> nums;
    static int n;

    // 소수 체크
    static boolean chk(int n) {
        for (int i = 2; i < n - 1; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // dfs
    static void DFS(String v) {
        int num = Integer.parseInt(v);
        if (!chk(num)) {
            return;
        }
        if (v.length() == n) {
            nums.add(num);
            return;
        }
        for (int i = 0; i < 10; i++) {
            DFS(v + String.valueOf(i));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new ArrayList<>();
        for (int i = 2; i < 10; i++) {
            DFS(String.valueOf(i));
        }
        for (int ans : nums) {
            System.out.println(ans);
        }
    }
}
