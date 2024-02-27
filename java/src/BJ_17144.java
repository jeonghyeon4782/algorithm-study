import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_17144 {
    static class Dust {
        int r, c, n;

        public Dust(int r, int c, int n) {
            this.r = r;
            this.c = c;
            this.n = n;
        }
    }

    // 상 하 좌 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] topD = {0, 3, 1, 2};    // 위에 있는 공기청정기 방향
    static int[] bottomD = {1, 3, 0, 2}; // 아래 있는 공기청정기 방향
    static Point topM;   // 위쪽 공기 청정기 좌표
    static Point bottomM;   // 아래쪽 공기 청정기 좌표
    static int R, C, T, result;
    static int[][] map;
    static List<Dust> dustList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        dustList = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1 && !flag) {
                    map[i][j] = -1;
                    topM = new Point(i, j);
                    flag = true;
                    continue;
                } else if (num == -1 && flag) {
                    map[i][j] = -1;
                    bottomM = new Point(i, j);
                    continue;
                }
                if (num != 0) {
                    dustList.add(new Dust(i, j, num));
                } else {
                    map[i][j] = num;
                }
            }
        }
        while (T-- > 0) {
            diffusion();    // 확산
            wind();       // 공기 청정기 바람
            findDust();     // 미세먼지 찾기
//            for (int i = 0; i < R; i++) System.out.println(Arrays.toString(map[i]));
//            System.out.println();
        }

        System.out.println(result);
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    private static void wind() {
        // 위쪽 바람 처리
        int nowX = topM.x - 1;
        int nowY = topM.y;
        int nowD = 0;
        while (true) {
            int nr  = nowX + dr[topD[nowD]];
            int nc  = nowY + dc[topD[nowD]];
            if (!isValid(nr, nc) || nr > topM.x) {
                nowD = (nowD + 1) % 4;
                continue;
            }
            if (nr == topM.x && nc == topM.y) break;
            map[nowX][nowY] = map[nr][nc];
            nowX = nr;
            nowY = nc;
        }
        map[topM.x][topM.y + 1] = 0;

        // 아래쪽 바람 처리
        nowX = bottomM.x + 1;
        nowY = bottomM.y;
        nowD = 0;
        while (true) {
            int nr  = nowX + dr[bottomD[nowD]];
            int nc  = nowY + dc[bottomD[nowD]];
            if (!isValid(nr, nc) || nr < bottomM.x) {
                nowD = (nowD + 1) % 4;
                continue;
            }
            if (nr == bottomM.x && nc == bottomM.y) break;
            map[nowX][nowY] = map[nr][nc];
            nowX = nr;
            nowY = nc;
        }
        map[bottomM.x][bottomM.y + 1] = 0;
    }

    private static void diffusion() {
        map = new int[R][C];
        map[topM.x][topM.y] = -1;
        map[bottomM.x][bottomM.y] = -1;
        for (Dust now : dustList) {
            int diffusionCnt = 0;   // 확산한 수
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                if (isValid(nr, nc) && map[nr][nc] != -1 && now.n / 5 >= 1) {
                    map[nr][nc] += now.n / 5;
                    ++diffusionCnt;
                }
            }
            map[now.r][now.c] += now.n - now.n / 5 * diffusionCnt;
        }
    }

    private static void findDust() {
        dustList = new ArrayList<>();
        int sumDust = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != 0 && map[i][j] != -1) {
                    dustList.add(new Dust(i, j, map[i][j]));
                    sumDust += map[i][j];
                }
            }
        }
        result = sumDust;
    }
}
