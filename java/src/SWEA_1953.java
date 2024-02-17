import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953 {

    // 상, 하, 좌, 우, 왼쪽 위, 왼쪽 아래, 오른쪽 아래, 오른쪽 위
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visited;
    static int N, M, R, C, L;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int p = 1; p <= test; p++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visited = new boolean[N][M];
            q = new LinkedList<>();
            int count = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            bfs();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visited[i][j]) count++;
                }
            }
            System.out.printf("#%d %d\n", p, count);
        }
    }

    private static void bfs() {
        q.offer(new int[]{R, C, 1});
        visited[R][C] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nx, ny;
            int nowX = now[0];
            int nowY = now[1];
            int time = now[2];

            if (time == L) continue;

            switch (map[nowX][nowY]) {
                case 1:
                    for (int i = 0; i < 4; i++) {
                        check(nowX, nowY, time, i);
                    }
                    break;
                case 2:
                    for (int i = 0; i < 2; i++) {
                        check(nowX, nowY, time, i);
                    }
                    break;
                case 3:
                    for (int i = 2; i < 4; i++) {
                        check(nowX, nowY, time, i);
                    }
                    break;
                case 4:
                    if (isValid(nowX - 1, nowY) && visited[nowX - 1][nowY]) {
                        check(nowX, nowY, time, 3);
                    } else if (visited[nowX][nowY + 1]) {
                        check(nowX, nowY, time, 0);
                    }
                    break;
                case 5:
                    if (isValid(nowX, nowY + 1) && visited[nowX][nowY + 1]) {
                        check(nowX, nowY, time, 1);
                    } else if (visited[nowX + 1][nowY]) {
                        check(nowX, nowY, time, 3);
                    }
                    break;
                case 6:
                    if (isValid(nowX, nowY - 1) && visited[nowX][nowY - 1]) {
                        check(nowX, nowY, time, 1);
                    } else if (visited[nowX + 1][nowY]) {
                        check(nowX, nowY, time, 2);
                    }
                    break;
                case 7:
                    if (isValid(nowX, nowY - 1) && visited[nowX][nowY - 1]) {
                        check(nowX, nowY, time, 0);
                    } else if (visited[nowX - 1][nowY]) {
                        check(nowX, nowY, time, 2);
                    }
                    break;
            }
        }
    }

    public static void check(int nowX, int nowY, int time, int idx) {
        int nx = nowX + dx[idx];
        int ny = nowY + dy[idx];
        if (isValid(nx, ny) && map[nx][ny] != 0 && !visited[nx][ny]) {
            switch (idx) {
                case 0:
                    if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6) {
                        q.offer(new int[]{nx, ny, time + 1});
                        visited[nx][ny] = true;
                    }
                    break;
                case 1:
                    if (map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7) {
                        q.offer(new int[]{nx, ny, time + 1});
                        visited[nx][ny] = true;
                    }
                    break;
                case 2:
                    if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5) {
                        q.offer(new int[]{nx, ny, time + 1});
                        visited[nx][ny] = true;
                    }
                    break;
                case 3:
                    if (map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7) {
                        q.offer(new int[]{nx, ny, time + 1});
                        visited[nx][ny] = true;
                    }
                    break;
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public static void showVisited() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(visited[i]));
        }
    }

    public static void showMap() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
