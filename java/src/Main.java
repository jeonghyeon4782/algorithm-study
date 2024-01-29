import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        arr = new int[2][6];
        int ans = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x][y - 1]++;
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                if (arr[i][j] % 2 == 0) {
                    ans += arr[i][j] / 2;
                } else {
                    ans += arr[i][j] / 2 + 1;
                }
            }
        }

        System.out.println(ans);
    }
}
