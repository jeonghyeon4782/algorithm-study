import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14890 {
    static int[][] map;
    static int n, l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
    }

    public static int solve() {
        int answer = 0; // 가능한 길의 수

        for (int i = 0; i < n; i++) {
            int c = map[i][0];  // 가장 처음의 숫자 셋팅
            int cnt = 0;    // 연속된 숫자의 수
            boolean flag = false;   // 만약 경사로가 있다면?
            boolean count = true;
            for (int j = 1; j < n; j++) {
                if (Math.abs(map[i][j] - c) > 1) {
                    count = false;
                    break;
                }

                if (cnt > l) {
                    flag = false;
                }

                if (flag && Math.abs(map[i][j] - c) == 1) {
                    count = false;
                    break;
                }

                if (Math.abs(map[i][j] - c) == 1) {
                    flag = true;
                    cnt = 1;
                } else {
                    cnt++;
                }
                c = map[i][j];
            }
            if (!flag && count) answer++;
        }
        return answer;
    }
}
