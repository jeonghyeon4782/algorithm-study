import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3109 {
    static int[] dx = {-1, 0, 1};
    static char[][] map;
    static int r, c, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < r; i++) {
            dfs(i, 0);
        }
        System.out.println(cnt);
    }

    private static boolean dfs(int x, int y) {
        map[x][y] = 'x';
        if (y == c - 1) {
            cnt++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + 1;

            if (0 <= nx && nx < r && 0 <= ny && ny < c && map[nx][ny] != 'x') {
                if (dfs(nx, ny)) {
                    return true;
                }
            }
        }
        return false;
    }
}
