import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7576 {
    // 수정 코드
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] A;
    static int n, m;
    static Queue<int[]> q;

    public static boolean is_valid(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }

    public static void bfs() {
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (is_valid(nx, ny) && A[nx][ny] == 0) {
                    A[nx][ny] = A[now[0]][now[1]] + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    public static int chk() {
        int maxNum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) {
                    return -1;
                }
                maxNum = Math.max(maxNum, A[i][j]);
            }
        }
        return maxNum - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = new int[m][n];
        q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        bfs();
        System.out.println(chk());
    }

    /* 시간 초과 코드
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][] A;
    static int n, m;

    public static boolean is_valid(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (is_valid(nx, ny) && A[nx][ny] != 1 && A[nx][ny] != -1 && (A[now[0]][now[1]] + 1) < A[nx][ny]) {
                    A[nx][ny] = (A[now[0]][now[1]]) + 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = new int[m][n];
        int maxNum = 0;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                A[i][j] = (num == 0) ? Integer.MAX_VALUE : num;
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxNum = Math.max(maxNum, A[i][j]);
            }
        }

        System.out.println((maxNum == Integer.MAX_VALUE) ? -1 : maxNum - 1);
    }
     */
}
