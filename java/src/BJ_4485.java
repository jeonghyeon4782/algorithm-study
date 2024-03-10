import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int N;
    static int[][] map, result;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int idx = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            map = new int[N][N];
            result = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    result[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra();
            System.out.printf("Problem %d: %d\n", idx++, result[N - 1][N - 1]);
        }
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        result[0][0] = map[0][0];
        visited[0][0] = true;
        pq.offer(new Node(0, 0, map[0][0]));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                if (isValid(nr, nc) && !visited[nr][nc] && now.weight + map[nr][nc] < result[nr][nc]) {
                    visited[nr][nc] = true;
                    result[nr][nc] = now.weight + map[nr][nc];
                    pq.offer(new Node(nr, nc, result[nr][nc]));
                }
            }
        }
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
