import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] chk;
    static int n, m, r;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < r; i++) {
            turn();
        }

        for (int k = 0; k < n; k++) {
            for (int l = 0; l < m; l++) {
                System.out.print(map[k][l] + " ");
            }
            System.out.println();
        }
    }

    public static boolean is_valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void turn() {
        chk = new boolean[n][m];
        for (int i = 0; i < Math.min(n, m) / 2; i++) {
            int d = 0;
            int x = i;
            int y = i;
            int temp = map[x][y];

            while (true) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (is_valid(nx, ny) && !chk[nx][ny]) {
                    map[x][y] = map[nx][ny];
                    chk[nx][ny] = true;
                    x = nx;
                    y = ny;
                    if (nx == i && ny == i) break;
                } else {
                    d = (d + 1) % 4;
                }
            }

            map[i + 1][i] = temp;
        }
    }
}
