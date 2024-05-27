import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_4179 {

    static class Coordinate {
        int r, c;
        int cnt;

        public Coordinate(int r, int c, int cnt) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Coordinate> manList;
    static Queue<Coordinate> fireList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        manList = new LinkedList<>();
        fireList = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'J') {
                    manList.add(new Coordinate(i, j, 1));
                    map[i][j] = '.';
                    visited[i][j] = true;
                } else if (map[i][j] == 'F') {
                    fireList.add(new Coordinate(i, j, 1));
                }
            }
        }
        bfs();
    }

    public static void bfs() {
        while (!manList.isEmpty()) {
            // 불 이동
            for (int i = 0, size = fireList.size(); i < size; i++) {

                Coordinate nowFire = fireList.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = nowFire.r + dx[d];
                    int nc = nowFire.c + dy[d];

                    if (isValid(nr, nc) && map[nr][nc] == '.') {
                        fireList.add(new Coordinate(nr, nc, nowFire.cnt + 1));
                        map[nr][nc] = 'F';
                    }
                }
            }
            // 지훈이 이동
            for (int i = 0, size = manList.size(); i < size; i++) {
                
                Coordinate nowMan = manList.poll();
                
                if (isEscape(nowMan.r, nowMan.c)) {
                    System.out.println(nowMan.cnt);
                    return;
                }
                
                for (int d = 0; d < 4; d++) {
                    int nr = nowMan.r + dx[d];
                    int nc = nowMan.c + dy[d];

                    if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] == '.') {
                        manList.add(new Coordinate(nr, nc, nowMan.cnt + 1));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
        System.out.println("IMPOSSIBLE");
    }

    public static boolean isValid(int r, int c) {
        return (r >= 0 && r < R && c >= 0 && c < C);
    }

    public static boolean isEscape(int r, int c) {
        return r == 0 || r == R - 1 || c == 0 || c == C - 1;
    }
}
