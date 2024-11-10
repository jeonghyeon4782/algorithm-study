import java.util.*;
import java.io.*;

public class BJ_14940 {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        int r = 0;
        int c = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    r = i;
                    c = j;
                }
            }
        }
        bfs(r, c);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] != 0) {
                    map[i][j] = -1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void bfs(int r, int c){
        Queue<int[]> queue = new LinkedList<>();    // 0 : r, 1 : c, 2 : 거리
        map[r][c] = 0;
        visited[r][c] = true;
        queue.offer(new int[]{r, c, 0});

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowR = now[0];
            int nowC = now[1];
            int nowD = now[2];

            for (int d = 0; d < 4; d++) {
                int newR = nowR + dr[d];
                int newC = nowC + dc[d];

                if (isValid(newR, newC) && !visited[newR][newC] && map[newR][newC] != 0) {
                    queue.offer(new int[]{newR, newC, nowD + 1});
                    map[newR][newC] = nowD + 1;
                    visited[newR][newC] = true;
                }
            }
        }
    }

    public static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
