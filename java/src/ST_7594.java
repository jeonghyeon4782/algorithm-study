import java.io.*;
import java.util.*;

public class ST_7594 {

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 우, 하
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};
    static List<Point[]> combList;
    static int[][] map;
    static int[] input;
    static int N, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        combList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int d = 0; d < 2; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];
                    if (!isValid(nr, nc)) continue;
                    Point[] pointArr = new Point[2];
                    pointArr[0] = new Point(i, j);
                    pointArr[1] = new Point(nr, nc);
                    combList.add(pointArr);
                }
            }
        }

        for (int i = 1; i <= 4; i++) {
            input = new int[i];
            comb(0, 0, i);
        }

        System.out.println(ans);
    }

    public static void comb(int cnt, int start, int r) {

        if (cnt == r) {
            Set<String> set = new HashSet<>();
            for (int i = 0; i < r; i++) {
                Point[] comb = combList.get(input[i]);
                Point p1 = comb[0];
                Point p2 = comb[1];
                String ps1 = String.valueOf(p1.r) + " " + String.valueOf(p1.c);
                String ps2 = String.valueOf(p2.r) + " " + String.valueOf(p2.c);
                if (set.contains(ps1) || set.contains(ps2)) return;
                set.add(ps1);
                set.add(ps2);
            }

            int sum = 0;
            for (String s : set) {
                String[] sArr = s.split(" ");
                int t1 = Integer.parseInt(sArr[0]);
                int t2 = Integer.parseInt(sArr[1]);
                sum += map[t1][t2];
            }

            ans = Math.max(ans, sum);

            return;
        }

        for (int i = start; i < combList.size(); i++) {
            input[cnt] = i;
            comb(cnt + 1, i + 1, r);
        }
    }

    public static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}