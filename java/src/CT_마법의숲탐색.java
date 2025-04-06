import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CT_마법의숲탐색 {

    static class Point {
        int r, c, idx;

        public Point(int r, int c, int idx) {
            this.r = r;
            this.c = c;
            this.idx = idx;
        }
    }

    // 북, 동, 남, 서
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int R, C, K;
    static int[][] map;
    static boolean[][] isExit;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R + 3][C];
        isExit = new boolean[R + 3][C];
        int answer = 0;
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken()) - 1;
            int exit = Integer.parseInt(st.nextToken());

            // 골렘을 하단으로 이동시키기
            int[] newGolem = move(c, exit);

            // 만약 골렘이 맵 안으로 못들어 왔다면?
            if (newGolem[0] < 4) {
                map = new int[R + 3][C];
                isExit = new boolean[R + 3][C];
                continue;
            }

            // 정상적으로 들어왔다면 맵에 골렘을 표시
            mark(newGolem[0], newGolem[1], newGolem[2], i);
//			showMap();
//			showExit();

            // 정령의 이동의 최대 행 구하기
            int cnt = bfs(newGolem[0], newGolem[1], i);
//			System.out.println(cnt - 2);
//			System.out.println();
            answer += (cnt - 2);
        }
        System.out.println(answer);
    }

    // 최대 이동할 수 있는 행 구하기
    private static int bfs(int r, int c, int idx) {
        int maxR = r;
        Queue<Point> queue = new LinkedList<>();
        visited = new boolean[R + 3][C];
        queue.offer(new Point(r, c, idx));
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            maxR = Math.max(maxR, now.r);

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                // 현재 출구에 있다면?
                if (isExit[now.r][now.c]) {
                    if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0) {
                        visited[nr][nc] = true;
                        queue.offer(new Point(nr, nc, map[nr][nc]));
                    }
                }
                // 출구가 아니라면?
                else {
                    if (isValid(nr, nc) && !visited[nr][nc] && now.idx == map[nr][nc]) {
                        visited[nr][nc] = true;
                        queue.offer(new Point(nr, nc, now.idx));
                    }
                }
            }
        }

        return maxR;
    }


    // 골렘 중심을 기준으로 맵에 표시
    private static void mark(int r, int c, int exit, int idx) {

        map[r][c] = idx;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (!isValid(nr, nc)) continue; // 맵 밖이면 무시
            if (d == exit) {
                isExit[nr][nc] = true;
            }
            map[nr][nc] = idx;
        }
    }

    // 골렘을 최대한 하단으로 이동시키는 메서드
    private static int[] move(int c, int exit) {
        int r = 1;

        while (true) {

            // 하단 이동
            int nr = r + 1;
            int nc = c;
            if (check(nr, nc)) {
                r = nr;
                c = nc;
                continue;
            }

            // 왼쪽 이동 후 아래 이동
            nr = r;
            nc = c - 1;
            if (check(nr, nc)) {
                nr = nr + 1;
                if (check(nr, nc)) {
                    r = nr;
                    c = nc;
                    exit = exit - 1;
                    if (exit == -1) exit = 3;
                    continue;
                }
            }

            // 오른쪽 이동 후 아래 이동
            nr = r;
            nc = c + 1;
            if (check(nr, nc)) {
                nr = nr + 1;
                if (check(nr, nc)) {
                    r = nr;
                    c = nc;
                    exit = (exit + 1) % 4;
                    continue;
                }
            }

            break;
        }

        return new int[] {r, c, exit};
    }

    // 골렘이 이동이 가능한지 판별하는 메서드
    private static boolean check(int r, int c) {

        // 만약 맵 밖으로 벗어난다면?
        if (!(0 <= r && r < R + 3 && 0 <= c && c < C)) return false;
        // 만약 다른 골렘이 있다면?
        if (map[r][c] != 0) return false;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 만약 맵 밖으로 벗어난다면?
            if (!(0 <= nr && nr < R + 3 && 0 <= nc && nc < C)) return false;
            // 만약 다른 골렘이 있다면?
            if (map[nr][nc] != 0) return false;
        }

        return true;
    }

    // 맵의 현재 상태를 출력 메서드
    private static void showMap() {
        for (int i = 0; i < R + 3; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    // 맵의 현재 상태를 출력 메서드
    private static void showExit() {
        for (int i = 0; i < R + 3; i++) {
            System.out.println(Arrays.toString(isExit[i]));
        }
        System.out.println();
    }

    // bfs 이동 판별 메서드
    private static boolean isValid(int r, int c) {
        return 0 <= r && r < R + 3 && 0 <= c && c < C;
    }
}
