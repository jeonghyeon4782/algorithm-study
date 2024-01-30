import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14500 {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int n, m;
    static int[][] arr;
    static boolean[][] visited;
    static int ans;

    public static boolean is_valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void dfs(int x, int y, int cnt, int sumNum) {
        if (cnt == 4) {
            ans = Math.max(ans, sumNum);
            return;
        }

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (is_valid(nx, ny) && !visited[nx][ny]) {
                dfs(nx, ny, cnt + 1, sumNum + arr[nx][ny]);
            }
        }
        visited[x][y] = false; // 중요!!!!
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        ans = 0;
        visited = new boolean[n][m];

        for (int i = 0; i < n; i ++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, 1, arr[i][j]);
            }
        }

        for (int i = 0; i < n - 1; i ++) {
            for (int j = 0; j < m - 2; j++) {
                ans = Math.max(arr[i][j] + arr[i][j + 1] + arr[i][j + 2] + arr[i + 1][j + 1], ans);
            }
        }

        for (int i = 0; i < n - 1; i ++) {
            for (int j = 1; j < m - 1; j++) {
                ans = Math.max(arr[i][j] + arr[i + 1][j] + arr[i + 1][j + 1] + arr[i + 1][j - 1], ans);
            }
        }

        for (int i = 1; i < n - 1; i ++) {
            for (int j = 0; j < m - 1; j++) {
                ans = Math.max(arr[i][j] + arr[i - 1][j] + arr[i][j + 1] + arr[i + 1][j], ans);
            }
        }

        for (int i = 1; i < n - 1; i ++) {
            for (int j = 1; j < m; j++) {
                ans = Math.max(arr[i][j] + arr[i - 1][j] + arr[i + 1][j] + arr[i][j - 1], ans);
            }
        }

        System.out.println(ans);
    }
}
