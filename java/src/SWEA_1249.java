import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class SWEA_1249 {

    static class Node implements Comparable<Node> {
        int r, c, w;

        public Node(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static int N;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int[][] dist;
    static int[][] map;
    static boolean[][] visited;
    static StringBuilder sb;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int T = 1; T <= test; T++) {

            // 1. setting
            N = Integer.parseInt(br.readLine());
            dist = new int[N][N];
            map = new int[N][N];
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                String s = br.readLine();
                for (int c = 0; c < N; c++) {
                    map[r][c] = s.charAt(c) - '0';
                    dist[r][c] = Integer.MAX_VALUE;
                }
            }

            // 2. 다익스트라
            solve(T);
        }

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static void solve(int T) {
        pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.r == N - 1 && now.c == N - 1) {
                sb.append("#").append(T).append(" ").append(dist[now.r][now.c]).append("\n");
                break;
            }
            if (!visited[now.r][now.c]) visited[now.r][now.c] = true;

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (isValid(nr, nc) && !visited[nr][nc] && now.w + map[nr][nc] < dist[nr][nc]) {
                    dist[nr][nc] = now.w + map[nr][nc];
                    pq.offer(new Node(nr, nc, dist[nr][nc]));
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
