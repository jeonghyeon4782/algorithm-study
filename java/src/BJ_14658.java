import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_14658 {

    static int N, M, L, K;   //
    static List<Point> starList;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        starList = new ArrayList<Point>();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            starList.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        for (Point now : starList) {
            if (isValid(now.x - L, now.x, now.y - L, now.y)) {
                int cnt = 0;
                for (Point now2 : starList) {
                    if (now.x == now2.x && now.y == now2.y) {
                        cnt++;
                        continue;
                    }
                    if (solve(now.x - L, now.x, now.y - L, now.y, now2.x, now2.y)) cnt++;
                }
                result = Math.max(result, cnt);
            }
            if (isValid(now.x - L, now.x, now.y, now.y + L)) {
                int cnt = 0;
                for (Point now2 : starList) {
                    if (now.x == now2.x && now.y == now2.y) {
                        cnt++;
                        continue;
                    }
                    if (solve(now.x - L, now.x, now.y, now.y + L, now2.x, now2.y)) cnt++;
                }
                result = Math.max(result, cnt);
            }
            if (isValid(now.x, now.x + L, now.y - L, now.y)) {
                int cnt = 0;
                for (Point now2 : starList) {
                    if (now.x == now2.x && now.y == now2.y) {
                        cnt++;
                        continue;
                    }
                    if (solve(now.x, now.x + L, now.y - L, now.y, now2.x, now2.y)) cnt++;
                }
                result = Math.max(result, cnt);
            }
            if (isValid(now.x, now.x + L, now.y, now.y + L)) {
                int cnt = 0;
                for (Point now2 : starList) {
                    if (now.x == now2.x && now.y == now2.y) {
                        cnt++;
                        continue;
                    }
                    if (solve(now.x, now.x + L, now.y, now.y + L, now2.x, now2.y)) cnt++;
                }
                result = Math.max(result, cnt);
            }
        }
        System.out.println(K - result);
    }

    public static boolean isValid(int startX, int endX, int startY, int endY) {
        return 0 <= startX && 0 <= startY && endX <= N && endY <= M;
    }

    public static boolean solve (int startX, int endX, int startY, int endY, int x, int y) {
        return startX <= x && x <= endX && startY <= y && y <= endY;
    }
}