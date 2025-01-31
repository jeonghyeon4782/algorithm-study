import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_4485 {

    static class Node implements Comparable<Node> {
        int r, c, weight;

        public Node(int r, int c, int weight) {
            this.r = r;
            this.c = c;
            this.weight = weight;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static int N;
    static int[][] dist;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int idx = 1;

        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) return;
            map = new int[N][N];
            dist = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            solve(idx++);
        }
    }

    private static void solve(int idx) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, map[0][0]));
        visited[0][0] = true;
        dist[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.r == N - 1 && now.c == N - 1) {
                System.out.printf("Problem %d: %d\n", idx, dist[now.r][now.c]);
                return;
            }

            if (!visited[now.r][now.c]) visited[now.r][now.c] = true;

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (isValid(nr, nc) && !visited[nr][nc] && now.weight + map[nr][nc] < dist[nr][nc]) {
                    dist[nr][nc] = now.weight + map[nr][nc];
                    pq.offer(new Node(nr, nc, dist[nr][nc]));
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
