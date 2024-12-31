import java.util.Arrays;
import java.util.Scanner;

public class BJ_16927 {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int N, M, R;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        R = sc.nextInt();
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < R; i++) {
            visited = new boolean[N][M];
            rotate();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void rotate() {
        for (int s = 0; s < N / 2; s++) {
            int r = s;
            int c = s;
            int d = 0;
            int startNum = map[r][c];

            while (true) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!isValid(nr, nc) || visited[nr][nc]) {
                    d = (d + 1) % 4;
                    continue;
                }

                map[r][c] = map[nr][nc];
                visited[nr][nc] = true;
                r = nr;
                c = nc;

                if (nr == s && nc == s) break;
            }

            map[s + 1][s] = startNum;
        }
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < N && c >= 0 && c < M;
    }
}
