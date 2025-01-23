import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2178 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int R, C;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
        bfs(0, 0, 1);
    }

    private static void bfs(int r, int c, int depth) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c, depth});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            if (now[0] == R - 1 && now[1] == C - 1) {
                System.out.println(now[2]);
                return;
            }
            for (int d = 0; d < 4; d++) {
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
                    q.offer(new int[] {nr, nc, now[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
