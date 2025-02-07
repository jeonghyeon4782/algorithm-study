import java.io.*;
import java.util.*;

public class ST_7726 {

    static class Point {
        int r, c, cnt;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static List<Point> ghostList;
    static Point start, exit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        ghostList = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'G') ghostList.add(new Point(i, j));
                else if (map[i][j] == 'D') exit = new Point(i, j);
                else if (map[i][j] == 'N') start = new Point(i, j);
            }
        }
        int minCount = bfs();
        if (minCount == -1) {
            System.out.println("No");
            return;
        }

        for (Point ghost : ghostList) {
            int dist = Math.abs(exit.r - ghost.r) + Math.abs(exit.c - ghost.c);
            if (minCount >= dist) {
                System.out.println("No");
                return;
            }
        }

        System.out.println("Yes");
    }

    public static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(start.r, start.c, 0));
        visited[start.r][start.c] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.r == exit.r && now.c == exit.c) {
                return now.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] != '#') {
                    q.offer(new Point(nr, nc, now.cnt + 1));
                    visited[nr][nc] = true;
                }
            }
        }

        return -1;
    }

    public static boolean isValid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}