import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_9663 {
    static int[] arr;
    static int n, cnt;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        dfs(0);
        System.out.println(cnt);
    }

    private static void dfs(int idx) {
        if (idx == n) {
            cnt++;
            return;
        }

        for (int i = 0; i < n; i++) {
            arr[idx] = i;
            if (check(idx)) dfs(idx + 1);
        }
    }

    private static boolean check(int idx) {

        for (int i = 0; i < idx; i++) {
            if (arr[i] == arr[idx]) return false;
            if (Math.abs(idx - i) == Math.abs(arr[idx] - arr[i])) return false;
        }

        return true;
    }
}