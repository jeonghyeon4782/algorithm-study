import java.io.*;
import java.util.*;

public class ST_7727 {

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n, m, ans;
    static Point[] friends;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        friends = new Point[m];
        visited = new boolean[n][n];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 친구들 위치 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            friends[i] = new Point(r, c);
            visited[r][c] = true;
            sum += map[r][c];
        }

        dfs(0, sum, friends);
        System.out.println(ans);
    }

    private static void dfs(int time, int sum, Point[] friends) {

        if (time == 3) {
            ans = Math.max(ans, sum);
            return;
        }

        moveFriends(0, time, sum, friends);
    }

    private static void moveFriends(int depth, int time, int sum, Point[] friends) {

        if (depth == m) {
            dfs(time + 1, sum, friends);
            return;
        }

        Point now = friends[depth];

        for (int d = 0; d < 4; d++) {
            int nr = now.r + dr[d];
            int nc = now.c + dc[d];

            if (!isValid(nr, nc)) continue;
            boolean wasVisited = visited[nr][nc];
            int prevR = now.r;
            int prevC = now.c;
            visited[nr][nc] = true;

            if (!wasVisited) {
                friends[depth] = new Point(nr, nc);
                moveFriends(depth + 1, time, sum + map[nr][nc], friends);
            } else {
                friends[depth] = new Point(nr, nc);
                moveFriends(depth + 1, time, sum, friends);
            }
            visited[nr][nc] = wasVisited;
            friends[depth] = new Point(prevR, prevC);
        }
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < n;
    }
}
