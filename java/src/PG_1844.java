import java.util.*;

class Point {
    int r, c, cnt;

    public Point(int r, int c, int cnt) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
    }
}

class Solution {
    public int solution(int[][] maps) {
        return bfs(maps);
    }

    public int bfs(int[][] maps) {
        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, 1));
        visited[0][0] = true;

        while(!q.isEmpty()) {
            Point now = q.poll();

            if (now.r == maps.length - 1 && now.c == maps[0].length - 1) {
                return now.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nr = now.r + dr[i];
                int nc = now.c + dc[i];

                if (isMoved(nr, nc, maps.length, maps[0].length)
                        && !visited[nr][nc] && !(maps[nr][nc] == 0)) {
                    q.offer(new Point(nr, nc, now.cnt + 1));
                    visited[nr][nc] = true;
                }
            }

        }

        return -1;
    }

    public boolean isMoved(int r, int c, int R, int C) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}