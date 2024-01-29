import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14503 {
    // 북 동 남 서
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n, m, x, y, d;
    static int[][] arr; // 0 : 청소 X, 1 : 벽, 2 : 청소 O

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 가로 길이
        m = Integer.parseInt(st.nextToken()); // 세로 길이
        arr = new int[n][m];
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken()); // 청소기 위치 x
        y = Integer.parseInt(st.nextToken()); // 청소기 위치 y
        d = Integer.parseInt(st.nextToken()); // 현재 청소기 방향
        int count = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {

            boolean chk = false;

            // 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (arr[x][y] == 0) {
                arr[x][y] = 2;
                count++;
            }

            // 현재 칸의 주변 4칸 중 청소가 되었는 칸이 있는지 여부
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (arr[nx][ny] == 0) {
                    chk = true;
                }
            }


            if (chk) { // chk가 true일 경우
                d = (d + 3) % 4; // 방향을 반 시계 방향으로 회전
                int nx = x + dx[d];
                int ny = y + dy[d];
                // 바라보는 방향의 앞 쪽 칸이 청소되지 않은 경우 한칸 전진
                if (arr[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                }
            } else { // chk가 false일 경우
                int nd = (d + 2) % 4; // 반대 방향
                int nx = x + dx[nd];
                int ny = y + dy[nd];
                if (arr[nx][ny] != 1) {
                    x = nx;
                    y = ny;
                } else {
                    break;
                }
            }
        }

        System.out.println(count);
    }
}