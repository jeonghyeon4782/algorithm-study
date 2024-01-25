import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1987 {
    static String[][] A;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int n, m;
    static int maxNum = 0;

    public static boolean is_valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static void dfs(int x, int y, String s) {
        if (s.length() > maxNum) {
            maxNum = s.length();
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (is_valid(nx, ny) && !(s.contains(A[nx][ny]))) {
                dfs(nx, ny, s + A[nx][ny]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = new String[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < m; j++) {
                A[i][j] = String.valueOf(line.charAt(j));
            }
        }

        dfs(0, 0, A[0][0]);
        System.out.println(maxNum);
    }
}
