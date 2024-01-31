import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2573 {

    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int n, m;
    private static int[][] arr;
    private static boolean[][] chk;
    static List<int[]> minusList;

    public static boolean is_valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void search(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (is_valid(nx, ny) && arr[nx][ny] == 0) {
                cnt++;
            }
        }
        minusList.add(new int[] {x, y, cnt});
    }

    public static void bfs(int x, int y) {

        Queue<int[]> q = new LinkedList<>();
        chk[x][y] = true;
        q.offer(new int[] {x, y});

        while (!q.isEmpty()) {
            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (is_valid(nx, ny) && arr[nx][ny] != 0 && !chk[nx][ny]) {
                    q.offer(new int[]{nx, ny});
                    chk[nx][ny] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        chk = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int idx = 0;

        while (true) {

            boolean stop = true; // 만약 모두 0이라면
            chk = new boolean[n][m]; // 찾을 때마다 초기화
            int cnt = 0; // 분리된 빙산의 갯수

            minusList = new ArrayList<>();
            // 모든 빙산 녹이기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // 만약 빙산을 발견한다면 주변 빙산 탐색 후 - 실행
                    if (arr[i][j] != 0) {
                        search(i, j);
                        stop = false; // 모두 0이면 멈춘다.
                    }
                }
            }

            for (int[] a : minusList) {
                arr[a[0]][a[1]] -= a[2];
                if (arr[a[0]][a[1]] < 0) arr[a[0]][a[1]] = 0;
            }

            idx++; // 한번 녹일 경우 카운트 증가
            if (stop) {
                System.out.println(0);
                break;
            }

            // 분리된 빙산의 수 구하기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] != 0 && !chk[i][j]) {
                        bfs(i, j);
                        cnt++;
                    }
                }
            }

            if (cnt >= 2) {
                System.out.println(idx);
                break;
            }
        }
    }
}