import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_4179 {

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Point> jihunQueue;
    static Queue<Point> fireQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        jihunQueue = new LinkedList<>();
        fireQueue = new LinkedList<>();
        for (int r = 0; r < R; r++) {
            String s = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = s.charAt(c);
                if (map[r][c] == 'J') {
                    visited[r][c] = true;
                    jihunQueue.offer(new Point(r, c));
                } else if (map[r][c] == 'F') {
                    fireQueue.offer(new Point(r, c));
                }
            }
        }

        int time = 0;
        while (!jihunQueue.isEmpty()) {
            ++time;
            int size = 0;

            size = fireQueue.size();
            for (int i = 0; i < size; i++) {
                Point now = fireQueue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = now.r + dr[d];
                    int nc = now.c + dc[d];
                    if (!exit(nr, nc) && map[nr][nc] != 'F' && map[nr][nc] != '#') {
                        fireQueue.offer(new Point(nr, nc));
                        map[nr][nc] = 'F';
                    }
                }
            }

            size = jihunQueue.size();
            for (int i = 0; i < size; i++) {
                Point now = jihunQueue.poll();
                for (int d = 0; d < 4; d++) {
                    int nr = now.r + dr[d];
                    int nc = now.c + dc[d];
                    if (exit(nr, nc)) {
                        System.out.println(time);
                        return;
                    }
                    if (!visited[nr][nc] && map[nr][nc] != 'F' && map[nr][nc] != '#') {
                        jihunQueue.offer(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }

    // 탈출 여부 메서드
    public static boolean exit(int r, int c) {
        return !(0 <= r && r < R && 0 <= c && c < C);
    }
}
