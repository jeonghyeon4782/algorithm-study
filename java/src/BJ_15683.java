import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_15683 {
    static class CCTV {
        int r, c, num;

        public CCTV(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][][] distList = {
            {},
            {{0}, {1}, {2}, {3}},
            {{0, 1}, {2, 3}},
            {{0, 3}, {1, 3}, {1, 2}, {0, 2}},
            {{0, 1, 2}, {0, 1, 3}, {0, 2, 3}, {1, 2, 3}},
            {{0, 1, 2, 3}}
    };
    static int R, C;
    static int[][] map;
    static List<CCTV> cctvList;
    static int total, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        cctvList = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (1 <= map[i][j] && map[i][j] <= 6) ++total;
                if (1 <= map[i][j] && map[i][j] <= 5) {
                    cctvList.add(new CCTV(i, j, map[i][j]));
                }
            }
        }
        answer = Integer.MAX_VALUE;
        total = R * C - total;
        dfs(0, new boolean[R][C]);
        System.out.println(answer);
    }

    private static void dfs(int depth, boolean[][] visited) {
        if (depth == cctvList.size()) {
//            for (int r = 0; r < R; r++) {
//                System.out.println(Arrays.toString(visited[r]));
//            }
//            System.out.println();
            int cnt = 0;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (visited[r][c]) ++cnt;
                }
            }
            answer = Math.min(answer, total - cnt);
            return;
        }

        CCTV cctv = cctvList.get(depth);

        for (int[] arr : distList[cctv.num]) {
            boolean[][] newVisited = new boolean[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    newVisited[r][c] = visited[r][c];
                }
            }

            dfs(depth + 1, solve(cctv, newVisited, arr));
        }
    }

    private static boolean[][] solve(CCTV cctv, boolean[][] visited, int[] dist) {
        for (int d : dist) {
            int r = cctv.r;
            int c = cctv.c;
            while (true) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!isValid(nr, nc)) break;
                if (map[nr][nc] == 6) break;
                if (!(1 <= map[nr][nc] && map[nr][nc] <= 5)) visited[nr][nc] = true;

                r = nr;
                c = nc;
            }
        }

        return visited;
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}