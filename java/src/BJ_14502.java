import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_14502 {

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int n, m;
    static Pos[] input; // 벽 세울 곳을 담을 배열
    static List<Pos> emptyList;
    static int[][] map; // 지도
    static int[][] cMap; // 지도 복사본
    static int maxCnt = 0; // 안전영역 갯수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        input = new Pos[3];
        map = new int[n][m];
        cMap = new int[n][m];
        emptyList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    emptyList.add(new Pos(i, j));
                }
            }
        }

        combination(0, 0);
        System.out.println(maxCnt);
    }

    public static void bfs(int x, int y) {
        Pos p = new Pos(x, y);
        Queue<Pos> q = new LinkedList<Pos>();
        q.offer(p);

        while (!q.isEmpty()) {
            Pos now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (isValid(nx, ny) && cMap[nx][ny] == 0) {
                    q.offer(new Pos(nx, ny));
                    cMap[nx][ny] = 2;
                }
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void combination(int cnt, int start) {
        if (cnt == 3) {
            for (int i = 0; i < n; i++) {
                cMap[i] = Arrays.copyOf(map[i], m);
            }
            int count = 0;
            // 벽 세우기
            for (int i = 0; i < 3; i++) {
                cMap[input[i].x][input[i].y] = 1;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (cMap[i][j] == 2) {
                        bfs(i, j);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (cMap[i][j] == 0) {
                        count++;
                    }
                }
            }

            maxCnt = Math.max(count, maxCnt);

            return;
        }

        for (int i = start; i < emptyList.size(); i++) {
            input[cnt] = emptyList.get(i);
            combination(cnt + 1, i + 1);
        }
    }
}
