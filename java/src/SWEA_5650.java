import java.util.Scanner;

public class SWEA_5650 {
    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] reverse = {{}, {1, 3, 0, 2}, {3, 0, 1, 2}, {2, 0, 3, 1}, {1, 2, 3, 0}, {1, 0, 3, 2}};
    static int N, result;
    static int[][] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    for (int d = 0; d < 4; d++) {
                        play(i, j, d);
                    }
                }
            }
        }
        System.out.println(result);
    }

    private static void play(int r, int c, int d) {
        int startR = r;
        int startC = c;
        int score = 0;
        boolean start = false;

        while (true) {
            // 이동
            r = r + dr[d];
            c = c + dc[d];

            if (r == startR && c == startC && start) break;
            if (isValid(r, c) && map[r][c] == -1) break;

            if (!isValid(r, c)) {
                d = reverse[5][d];
                start = true;
                score++;
                continue;
            }

            if (1 <= map[r][c] && map[r][c] <= 5) {
                d = reverse[map[r][c]][d];
                score++;
            } else if (6 <= map[r][c] && map[r][c] <= 10) {
                int[] whiteHole = whileHole(r, c);
                r = whiteHole[0];
                c = whiteHole[1];
            }
            start = true;
        }
        result = Math.max(score, result);
    }

    private static int[] whileHole (int r, int c) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == map[r][c] && i != r && j != c) return new int[] {i, j};
            }
        }
        return new int[] {};
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
