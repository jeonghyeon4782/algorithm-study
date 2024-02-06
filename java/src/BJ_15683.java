import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_15683 {

    // 상 우 하 좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static int[][] cMap;
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        cMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cMap[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctv(i, j, map[i][j]);
                    for (int t = 0; t < n; t++) {
                        System.out.println(Arrays.toString(cMap[t]));
                    }
                    System.out.println();
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cMap[i][j] == 0) answer++;
            }
        }
        System.out.println(answer);
    }

    static boolean is_valid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void cctv(int x, int y, int r) {
        int[] count = cctvCheck(x, y);
        switch (r) {
            case 1:
                int maxCount = 0;
                int dist = 0;
                for (int i = 0; i < 4; i++) {
                    if (count[i] > maxCount) {
                        maxCount = count[i];
                        dist = i;
                    }
                }
                change(x, y, dist);
                break;
            case 2:
                // 상하로 했을 때 사각지대가 더 많다면
                if (count[0] + count[2] > count[1] + count[3]) {
                    change(x, y, 0);
                    change(x, y, 2);
                } else {
                    change(x, y, 1);
                    change(x, y, 3);
                }
                break;
            case 3:
                int maxNum = 0;
                for (int i = 0; i < 4; i++) {
                    if (i == 3) {
                        if (maxNum < count[3] + count[0]) maxNum = count[3] + count[0];
                    } else {
                        if (maxNum < count[i] + count[i + 1]) maxNum = count[i] + count[i + 1];
                    }
                }
                if (count[0] + count[1] == maxNum) {
                    change(x, y, 0);
                    change(x, y, 1);
                }
                else if (count[1] + count[2] == maxNum) {
                    change(x, y, 1);
                    change(x, y, 2);
                }
                else if (count[2] + count[3] == maxNum) {
                    change(x, y, 2);
                    change(x, y, 3);
                }
                else if (count[3] + count[0] == maxNum) {
                    change(x, y, 3);
                    change(x, y, 0);
                }
                break;
            case 4:
                maxNum = 0;
                for (int i = 0; i < 4; i++) {
                    if (i == 2) {
                        if (maxNum < count[2] + count[3] + count[0]) maxNum = count[2] + count[3] + count[0];
                    } else if (i == 3) {
                        if (maxNum < count[3] + count[0] + count[1]) maxNum = count[3] + count[0] + count[1];
                    } else {
                        if (maxNum < count[i] + count[i + 1] + count[i + 2]) maxNum = count[i] + count[i + 1] + count[i + 2];
                    }
                }
                if (count[0] + count[1] + count[2] == maxNum) {
                    change(x, y, 0);
                    change(x, y, 1);
                    change(x, y, 2);
                }
                else if (count[1] + count[2] + count[3] == maxNum) {
                    change(x, y, 1);
                    change(x, y, 2);
                    change(x, y, 3);
                }
                else if (count[2] + count[3] + count[0] == maxNum) {
                    change(x, y, 2);
                    change(x, y, 3);
                    change(x, y, 0);
                }
                else if (count[3] + count[0] + count[1] == maxNum) {
                    change(x, y, 3);
                    change(x, y, 0);
                    change(x, y, 1);
                }
                break;
            case 5:
                change(x, y, 1);
                change(x, y, 2);
                change(x, y, 3);
                change(x, y, 0);
                break;
        }
    }

    public static int[] cctvCheck(int x, int y) {
        int[] count = new int[4];

        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            while (true) {
                nx = nx + dx[i];
                ny = ny + dy[i];
                if (!is_valid(nx, ny)) break;
                else if (cMap[nx][ny] == 6) break;
                else if (cMap[nx][ny] == 0) count[i]++;
                else continue;
            }
        }
        return count;
    }

    public static void change(int x, int y, int r) {
        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            while (true) {
                nx = nx + dx[r];
                ny = ny + dy[r];
                if (!is_valid(nx, ny)) break;
                else if (cMap[nx][ny] == 6) break;
                else if (cMap[nx][ny] == 0) cMap[nx][ny] = -1;
                else continue;
            }
        }
    }
}
