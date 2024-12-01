import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_9205 {

    static int N;
    static Point[] beerStores;
    static Point start, end;
    static boolean answer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            answer = false;
            beerStores = new Point[N];
            visited = new boolean[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                beerStores[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            if (bfs(start.x, start.y)) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }

        }
    }

    private static boolean bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (Math.abs(now.x - end.x) + Math.abs(now.y - end.y) <= 1000) {
                return true;
            }

            for (int i = 0; i < N; i++) {
                if (!visited[i] && (Math.abs(now.x - beerStores[i].x) + Math.abs(now.y - beerStores[i].y) <= 1000)) {
                    q.offer(new Point(beerStores[i].x, beerStores[i].y));
                    visited[i] = true;
                }
            }
        }
        return false;
    }
}
