import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_20057 {
    //  좌 하 우 상
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};
    static int[][][] type = {
            {{}, {}},
            {{}, {}},
            {{}, {}},
            {{}, {}}
    };
    static int N, nowR, nowC, result;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i< N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        nowR = N / 2;   nowC = N / 2;
        solve();
        System.out.println(result);
    }

    private static void solve() {
        int d = 0;  // 이동 방향
        int s = 1;  // 이동 거리

        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < s; j++) {
                    if (nowR == 0 && nowC == 0) return; // 만약 시작 지점으로 돌아 왔으면 끝내기
                    int nr = nowR + dr[d];
                    int nc = nowC + dc[d];
                    switch (d) {
                        case 0:
                            left(nr, nc, d);
                            break;
                        case 1:
                            down(nr, nc, d);
                            break;
                        case 2:
                            right(nr, nc, d);
                            break;
                        case 3:
                            up(nr, nc, d);
                            break;
                    }
                    nowR = nr;
                    nowC = nc;
                }
                d = (d + 1) % 4;    // 방향 바꾸기
            }
            ++s;    // 이동 거리 늘려주기
        }
    }

    private static boolean isValid (int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    // 왼쪽으로 이동 시
    private static void left(int r, int c, int d) {
        int[] dx =  {-2, -1, -1, -1, 0, 1, 1, 1, 2};
        int[] dy =  {0, -1, 0, 1, -2, -1, 0, 1, 0};
        double[] percent = {2.0, 10.0, 7.0, 1.0, 5.0, 10.0, 7.0, 1.0, 2.0};
        check(r, c, d, dx, dy, percent);
    }

    // 아래쪽으로 이동 시
    private static void down(int r, int c, int d) {
        int[] dx =  {-1, -1, 0, 0, 0, 0, 1, 1, 2};
        int[] dy =  {-1, 1, -2, -1, 1, 2, -1, 1, 0};
        double[] percent = {1, 1, 2, 7, 7, 2, 10, 10, 5};
        check(r, c, d, dx, dy, percent);
    }

    // 오른쪽으로 이동 시
    private static void right(int r, int c, int d) {
        int[] dx =  {-2, -1, -1, -1, 0, 1, 1, 1, 2};
        int[] dy =  {0, -1, 0, 1, 2, -1, 0, 1, 0};
        double[] percent = {2, 1, 7, 10, 5, 1, 7, 10, 2};
        check(r, c, d, dx, dy, percent);
    }

    // 위쪽으로 이동 시
    private static void up(int r, int c, int d) {
        int[] dx =  {-2, -1, -1, 0, 0, 0, 0, 1, 1};
        int[] dy =  {0, -1, 1, -2, -1, 1, 2, -1, 1};
        double[] percent = {5, 10, 10, 2, 7, 7, 2, 1, 1};
        check(r, c, d, dx, dy, percent);
    }

    private static void check(int r, int c, int d, int[] dx, int[] dy, double[] percent) {
        int moveSand = 0;

        for (int i = 0; i < 9; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            int value = (int) Math.floor(map[r][c] * percent[i] / 100.0);   // 이동 할 모래의 값
            if (isValid(nr, nc)) {
                map[nr][nc] += value;
                moveSand += value;
            } else {
                result += value;
                moveSand += value;
            }
        }
        int nr = r + dr[d];
        int nc = c + dc[d];
        // 만약 격자 밖으로 나가지 않았다면
        if (isValid(nr, nc)) {
            map[nr][nc] += map[r][c] - moveSand;
        } else {
            result += map[r][c] - moveSand;
        }
        map[r][c] = 0;
    }
}
