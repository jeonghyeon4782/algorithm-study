import java.util.*;

class PG_67259 {

    class Point {
        int r, c, prevD, cost;
        public Point(int r, int c, int prevD, int cost) {
            this.r = r;
            this.c = c;
            this.prevD = prevD;
            this.cost = cost;
        }
    }

    int[][][] costMap;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};
    int N;

    public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        N = board.length;
        costMap = new int[N][N][4];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                for (int d = 0; d < 4; d++) {
                    costMap[r][c][d] = Integer.MAX_VALUE;
                }
            }
        }
        bfs(board);
        for (int d = 0; d < 4; d++) {
            answer = Math.min(costMap[N - 1][N - 1][d], answer);
        }
        return answer;
    }

    public void bfs(int[][] board) {
        for (int d = 0; d < 4; d++) {
            costMap[0][0][d] = 0;
        }
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, 1, 0));
        q.offer(new Point(0, 0, 3, 0));

        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                int prevD = now.prevD;
                int nCost = now.cost;
                if (!isValid(nr, nc) || board[nr][nc] == 1) continue;
                if (d != prevD) {
                    nCost += 600;
                } else {
                    nCost += 100;
                }
                if (nCost < costMap[nr][nc][d]) {
                    costMap[nr][nc][d] = nCost;
                    q.offer(new Point(nr, nc, d, nCost));
                }
            }
        }
    }

    public boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}