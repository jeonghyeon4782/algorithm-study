import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_3190 {
    static class Point {
        int r, c, d;
        public Point(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    // 동, 남, 서, 북
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static int[][] map; // 0 : 빈칸, 1 : 뱀, 2 : 사과
    static int N, K, L;
    static Deque<Point> snake;
    static Map<Integer, Character> infoMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N][N];
        snake = new ArrayDeque<>();
        map[0][0] = 1;
        snake.offerFirst(new Point(0, 0, 0));    // 머리 넣기
        snake.offerFirst(new Point(0, 0, 0));    // 꼬리 넣기
        infoMap = new HashMap<Integer, Character>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 2;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);

            infoMap.put(x, c);
        }

        int time = 0;
        while (true) {
            ++time;

            Point head = snake.pollLast();

            // 머리 이동
            head.r = head.r + dr[head.d];
            head.c = head.c + dc[head.d];

            // 만약 몸이 벽이나 자신과 부딪힌다면?
            if (!isValid(head.r, head.c) || map[head.r][head.c] == 1) {
                System.out.println(time);
                return;
            }

            // 만약 머리가 있는 곳이 사과라면?
            if (map[head.r][head.c] == 2) {
                snake.offerLast(new Point(head.r, head.c, head.d));
            }
            // 만약 머리가 있는 곳이 평지라면?
            else if (map[head.r][head.c] == 0) {
                // 꼬리 제거
                Point tail = snake.pollFirst();
                map[tail.r][tail.c] = 0;

                // 새로운 머리 추가
                snake.offerLast(new Point(head.r, head.c, head.d));
            }

            // 방향을 변경해야 한다면?
            if (infoMap.containsKey(time)) {
                char c = infoMap.get(time);
                if (c == 'L') {
                    head.d = (head.d + 3) % 4;
                } else if (c == 'D') {
                    head.d = (head.d + 1) % 4;
                }
            }

            snake.offerLast(head);
            // 머리 이동 방문 체크
            map[head.r][head.c] = 1;
        }
    }

    public static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
