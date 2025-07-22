import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_보급로 {

    static class Node implements Comparable<Node> {
        int r, c, w;
        public Node(int r, int c, int w) {
            this.r = r;
            this.c = c;
            this.w = w;
        }
        @Override
        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int N;
    static int[][] graph, dist;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(br.readLine());

        for (int T = 1; T <= test; T++) {

            // 1. setting
            N = Integer.parseInt(br.readLine());
            graph = new int[N][N];
            dist = new int[N][N];
            visited = new boolean[N][N];
            for (int r = 0; r < N; r++) {
                String s = br.readLine();
                for (int c = 0; c < N; c++) {
                    graph[r][c] = s.charAt(c) - '0';
                    dist[r][c] = Integer.MAX_VALUE;
                }
            }

            // 2. 다익스트라 실행
            dijkstra(0, 0, 0);
            sb.append(String.format("#%d %d\n", T, dist[N - 1][N - 1]));
        }
        System.out.println(sb);
    }

    private static void dijkstra(int r, int c, int w) {
        dist[r][c] = w;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(r, c, w));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.r == N - 1 && now.c == N - 1) {
                return;
            }
            if (!visited[now.r][now.c]) visited[now.r][now.c] = true;
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                if (isValid(nr, nc) && !visited[nr][nc] && now.w + graph[nr][nc] < dist[nr][nc]) {
                    dist[nr][nc] = now.w + graph[nr][nc];
                    pq.offer(new Node(nr, nc, dist[nr][nc]));
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
