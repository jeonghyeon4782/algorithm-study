import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class BJ_2636 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int R, C, result, hour;
    static int[][] map;
    static boolean[][] visited;
    static List<Point> removeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        result = cheeseCount();
        while (true) {
            int cnt = 0;
            melt();
            ++hour;
            cnt = cheeseCount();
            if (cnt == 0) {
                break;
            } else {
                result = cnt;
            }
        }
        System.out.println(hour);
        System.out.println(result);
    }

    // 치즈 녹이기
    public static void melt() {
        removeList = new ArrayList<>();
        visited = new boolean[R][C];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now.x + dr[d];
                int nc = now.y + dc[d];

                if (!isValid(nr, nc)) continue;
                if (visited[nr][nc]) continue;

                if (map[nr][nc] == 0) {
                    q.offer((new Point(nr, nc)));
                    visited[nr][nc] = true;
                } else if (map[nr][nc] == 1) {
                    removeList.add(new Point(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }

        for (Point p : removeList) {
            map[p.x][p.y] = 0;
        }
    }

    // 치즈 갯수 세기
    public static int cheeseCount() {
        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 1) ++result;
            }
        }
        return result;
    }

    // 맵 밖으로 나가는지 검사
    public static boolean isValid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
