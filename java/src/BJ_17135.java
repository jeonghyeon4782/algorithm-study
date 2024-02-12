import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_17135 {

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {-1, 1, 0, 0};
    static int n, m, d;
    static int[][] map;
    static int[][] cMap;
    static Pos[] input;        // 궁수 위치 조합을 넣을 곳 (3명)
    static List<Pos> emptyList;        // 빈칸들 리스트
    static Pos[] attack;        // 공격당한 위치


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        cMap = new int[n][m];
        input = new Pos[3];
        emptyList = new ArrayList<Pos>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cMap[i][j] = map[i][j];
                if (map[i][j] == 0) emptyList.add(new Pos(i, j));
            }
        }

        down();
        down();
        showCmap();
    }

    public static void showMap() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    public static void showCmap() {
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(cMap[i]));
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void comb(int cnt, int start) {
        if (cnt == 3) {
            // 궁수 추가
            for (Pos p : input) {
                cMap[p.x][p.y] = 2;     // 궁수는 2
            }

            // 원상 복귀
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    cMap[i][j] = map[i][j];
                }
            }
            return;
        }

        for (int i = start; i < emptyList.size(); i++) {
            input[cnt] = emptyList.get(i);
            comb(cnt + 1, i + 1);
        }
    }

    public static void down() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cMap[i][j] == 1) {
                    if (isValid(i + 1, j)) {
                        cMap[i][j] = 0;
                        cMap[i + 1][j] = 1;
                    } else {
                        cMap[i][j] = 0;
                    }
                }
            }
        }
    }

    public static boolean Check() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cMap[i][j] == 1) return false;
            }
        }
        return true;
    }

    public static void bfs(int x, int y) {

    }
}
