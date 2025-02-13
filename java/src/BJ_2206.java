import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2206 {

    static class Point {
        int r, c, depth;
        boolean flag;

        public Point(int r, int c, int depth, boolean flag) {
            this.r = r;
            this.c = c;
            this.depth = depth;
            this.flag = flag;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    ", depth=" + depth +
                    ", flag=" + flag +
                    '}';
        }
    }

    static int R, C;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;
    static int[][] dist;    // 별 뚫X
    static int[][] dist2;   // 벽 뚫O

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];
        dist = new int[R][C];
        dist2 = new int[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j) - '0';
                dist[i][j] = Integer.MAX_VALUE;
                dist2[i][j] = Integer.MAX_VALUE;
            }
        }
        bfs(0, 0, 1, false);

        int ans = Math.min(dist[R - 1][C - 1], dist2[R - 1][C - 1]);
        System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
    }

    private static void bfs(int r, int c, int depth, boolean flag) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c, depth, flag));
        dist[0][0] = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.r == R - 1 && now.c == C - 1) {
                return;
            }

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (!isValid(nr, nc)) continue;

                // 내가 벽을 뚫고 온 녀석이라면?
                if (now.flag) {
                    if (map[nr][nc] == 1) continue;

                    if ((map[nr][nc] == 0 && dist2[nr][nc] == Integer.MAX_VALUE)) {
                        dist2[nr][nc] = now.depth + 1;
                        q.offer(new Point(nr, nc, now.depth + 1, true));
                    }
                }
                // 아직 벽을 뚫지 않은 녀석이라면?
                if (!now.flag) {
                    if (map[nr][nc] == 1 && dist2[nr][nc] == Integer.MAX_VALUE) {
                        dist2[nr][nc] = now.depth + 1;
                        q.offer(new Point(nr, nc, now.depth + 1, true));
                    }
                    if (map[nr][nc] == 0 && dist[nr][nc] == Integer.MAX_VALUE) {
                        dist[nr][nc] = now.depth + 1;
                        q.offer(new Point(nr, nc, now.depth + 1, false));
                    }
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
