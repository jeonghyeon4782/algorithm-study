import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656 {
    static int N, W, H, result;
    static int[][] map;
    static int[][] map_copy;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<Integer> q;
    static int[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int T = 1; T <= test; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            map_copy = new int[H][W];
            input = new int[N];
            result = Integer.MAX_VALUE;
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            perm(0);
            System.out.printf("#%d %d\n", T, result);
        }
    }

    private static void perm (int cnt) {
        if (cnt == N) {
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    map_copy[i][j] = map[i][j];
                }
            }
            for (int i : input) {
                shoot(i);
                down();
            }
            int count = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] != 0) count++;
                }
            }
            result = Math.min(result, count);
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    map[i][j] = map_copy[i][j];
                }
            }
            return;
        }

        for (int i = 0; i < W; i++) {
            input[cnt] = i;
            perm(cnt + 1);
        }
    }

    private static void shoot(int c) {
        int nr = 0;
        int nc = c;
        while (true) {
            if (!isValid(nr, nc)) break;
            if (map[nr][nc] != 0) {
                // 벽돌 부시기 시작
                dfs(nr, nc, map[nr][nc]);
                break;
            }
            nr++;
        }
    }

    private static void dfs(int r, int c, int n) {
        map[r][c] = 0;
        if (n == 1) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 1; j < n; j++) {
                int nr = r + dr[i] * j;
                int nc = c + dc[i] * j;
                if (isValid(nr, nc) && map[nr][nc] != 0) {
                    dfs(nr, nc, map[nr][nc]);
                }
            }
        }
    }

    private static void down() {
        for (int i = 0; i < W; i++) {
            q = new LinkedList<>();
            for (int j = H - 1; j >= 0; j--) {
                if (map[j][i] != 0) {
                    q.offer(map[j][i]);
                    map[j][i] = 0;
                }
            }
            if (q.size() == 0) continue;
            int size = q.size();
            for (int j = H - 1; j >= H - size; j--) {
                map[j][i] = q.poll();
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < H && 0 <= c && c < W;
    }
}
