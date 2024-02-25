import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953 {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] type = {
            {},
            {1, 1, 1, 1},
            {1, 1, 0, 0},
            {0, 0, 1, 1},
            {1, 0, 0, 1},
            {0, 1, 0, 1},
            {0, 1, 1, 0},
            {1, 0, 1, 0}
    };
    static int N, M, R, C, L, result;
    static boolean[][] visited;
    static int[][] map;
    static Queue<Point> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
    }

    private static void bfs() {
        q = new LinkedList<>();
        q.offer(new Point(R, C));
        visited[R][C] = true;

        while (--L > 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point now = q.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = now.x + dx[d];
                    int nc = now.y + dy[d];
                    if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] != 0 && type[map[now.x][now.y]][d] == 1 && type[map[nr][nc]]["1032".charAt(d) - '0'] == 1) {
                        q.add(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
            for (int i = 0 ; i < N; i++) {
                System.out.println(Arrays.toString(visited[i]));
            }
            System.out.println();
        }
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
