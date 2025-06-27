import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1210 {

    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int T = 0; T < 10; T++) {
            map = new int[100][100];
            boolean[][] visited = new boolean[100][100];
            int testcase = Integer.parseInt(br.readLine());
            Point now = null;
            int[] dr = {0, 0, -1};
            int[] dc = {-1, 1, 0};
            for (int r = 0; r < 100; r++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int c = 0; c < 100; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                    if (map[r][c] == 2) now = new Point(r, c);
                }
            }
            visited[now.x][now.y] = true;

            while (now.x != 0) {
                for (int d = 0; d < 3; d++) {
                    int nr = now.x + dr[d];
                    int nc = now.y + dc[d];
                    if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
                        now.x = nr;
                        now.y = nc;
                        visited[nr][nc] = true;
                        break;
                    }
                }
            }

            System.out.printf("#%d %d\n", testcase, now.y);
        }
    }

    public static boolean isValid(int r, int c) {
        return 0 <= r && r < 100 && 0 <= c && c < 100;
    }
}
