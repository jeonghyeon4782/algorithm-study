import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_14658 {

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M, L, K;
    static List<Point> stars;   // 별똥별의 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int max = Integer.MIN_VALUE;
        stars = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            stars.add(new Point(r, c));
        }

        for (Point star1 : stars) {
            for (Point star2 : stars) {
                int cnt = 0;
                int minR = star1.r;
                int maxR = star1.r + L;
                int minC = star2.c;
                int maxC = star2.c + L;
                for (Point next : stars) {
                    if (countStar(minR, maxR, minC, maxC, next.r, next.c)) ++cnt;
                }
                max = Math.max(max, cnt);
            }
        }

        System.out.println(K - max);
    }

    // 튕겨 나갈 수 있는지 여부 확인 메서드
    private static boolean countStar(int minR, int maxR, int minC, int maxC, int nextR, int nextC) {
        return minR <= nextR && nextR <= maxR && minC <= nextC && nextC <= maxC;
    }
}
