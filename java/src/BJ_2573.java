import java.util.*;
import java.io.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_2573 {

    static class Point {
        int r, c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    static int R, C;
    static int[][] map;
    static Queue<Point> q;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        q = new LinkedList<>();
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) q.offer(new Point(i, j));
            }
        }

        int day = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            visited = new boolean[R][C];
            day++;

            for (int i = 0; i < size; i++) {
                Point now = q.poll();
                visited[now.r][now.c] = true;
                int cnt = 0;

                for (int d = 0; d < 4; d++) {
                    int nr = now.r + dr[d];
                    int nc = now.c + dc[d];

                    if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] == 0) cnt++;
                }

                map[now.r][now.c] -= cnt;
                if (map[now.r][now.c] < 0) map[now.r][now.c] = 0;
                if (map[now.r][now.c] > 0) q.offer(now);
            }
//            System.out.println("day: " + day);
//            for (int i = 0; i < R; i++) {
//                System.out.println(Arrays.toString(map[i]));
//            }
//            System.out.println("countIsland : " + countIslands());
//            System.out.println();
            if (countIslands() >= 2) break;
        }

        if (countIslands() < 2) System.out.println(0);
        else System.out.println(day);
    }

    public static int countIslands() {
        visited = new boolean[R][C];
        int cnt = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void dfs (int r, int c) {
        visited[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (isValid(nr ,nc) && !visited[nr][nc] && map[nr][nc] != 0) {
                dfs(nr, nc);
            }
        }
    }

    public static boolean isValid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}