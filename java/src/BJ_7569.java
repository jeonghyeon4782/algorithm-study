import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7569 {

    static int[] dr = {0, 0, -1, 1, 0, 0};
    static int[] dc = {-1, 1, 0, 0, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};
    static int R, C, H, target;
    static int[][][] map;
    static Queue<int[]> q;  // 0 : 높이, 1 : 세로, 2 : 가로

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][R][C];
        q = new LinkedList<>();

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < R; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < C; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 0) target++;
                    if (map[i][j][k] == 1) q.offer(new int[] {i, j, k});
                }
            }
        }

        int day = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            day++;
            for (int i = 0; i < size; i++) {
                int[] now = q.poll();
                for (int d = 0; d < 6; d++) {
                    int nh = now[0] + dh[d];
                    int nr = now[1] + dr[d];
                    int nc = now[2] + dc[d];

                    if (isValid(nr, nc, nh) && map[nh][nr][nc] == 0) {
                        q.offer(new int[] {nh, nr, nc});
                        map[nh][nr][nc] = 1;
                        target--;
                    }
                }
            }
        }

        if (target != 0) {
            System.out.println(-1);
            return;
        }

        System.out.println(day - 1);
    }

    public static boolean isValid(int r, int c, int h) {
        return 0 <= r && r < R && 0 <= c && c < C && 0 <= h && h < H;
    }
}
