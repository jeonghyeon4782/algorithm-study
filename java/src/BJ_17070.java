import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17070 {
    static long[][][] D;
    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        D = new long[N][N][3];   // 0 : 가로, 1 : 대각선, 2 : 세로
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dp());
    }

    private static long dp() {
        D[0][1][0] = 1;
        for (int i = 2; i < N; i++) {
            if (map[0][i] == 1) continue;
            D[0][i][0] = D[0][i - 1][0];
        }
        for (int i = 1; i < N; i++) {
            for (int j = 2; j < N; j++) {
                if (map[i][j] == 1) continue;
                D[i][j][0] += D[i][j - 1][0] + D[i][j - 1][1];
                if (map[i - 1][j] == 0 && map[i][j - 1] == 0) {
                    D[i][j][1] += D[i - 1][j - 1][0] + D[i - 1][j - 1][1] + D[i - 1][j - 1][2];
                }
                D[i][j][2] += D[i - 1][j][1] + D[i - 1][j][2];
            }
        }
        return D[N - 1][N - 1][0] + D[N - 1][N - 1][1] + D[N - 1][N - 1][2];
    }
}
