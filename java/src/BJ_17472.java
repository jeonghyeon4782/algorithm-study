import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_17472 {
    static class Edge implements Comparable<Edge> {
        int from, to, dist;

        public Edge(int from, int to, int dist) {
            super();
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }

        @Override
        public String toString() {
            return "Edge [from=" + from + ", to=" + to + ", dist=" + dist + "]";
        }
    }

    static class Pos {
        int x, y, d, preD;

        public Pos(int x, int y, int d, int preD) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.preD = preD;
        }

        @Override
        public String toString() {
            return "Pos [x=" + x + ", y=" + y + ", d=" + d + ", preD=" + preD + "]";
        }
    }

    static int N, M, islandCnt;
    static boolean[][] visited;
    static int[][] map;
    static Edge[] edge;
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int[] input, parents;
    static List<Edge> edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        edgeList = new ArrayList<>();
        input = new int[2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    ++islandCnt;
                    dfs(i, j);
                }
            }
        }

        comb(0, 1);

        if (islandCnt - 1 > edgeList.size()) {
            System.out.println(-1);
            return;
        }

        parents = new int[islandCnt + 1];
        Collections.sort(edgeList);

        make();

        long ans = 0;
        int cnt = 0;
        for (Edge edge : edgeList) {
            if (!union(edge.from, edge.to)) {
                continue;
            }
            ans += edge.dist;
            if (++cnt == islandCnt - 1) {
                break;
            }
        }
        for (int i = 1; i < islandCnt - 1; i++) {
            if (find(i) != find(i + 1)) {
                System.out.println(-1);
                return;
            }
        }
        System.out.println(ans);
    }

    private static void make() {
        for (int i = 0; i < islandCnt + 1; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (parents[a] == a)
            return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot)
            return false;
        parents[bRoot] = aRoot;
        return true;
    }

    // 섬의 갯수 세기, 섬의 인덱스 바꾸기
    private static void dfs(int x, int y) {
        visited[x][y] = true;
        map[x][y] = islandCnt;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValid(nx, ny) && map[nx][ny] != 0 && !visited[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }

    // 각각의 섬들 간의 거리를 리스트에 추가
    private static int bfs(int x, int y, int target) {
        Queue<Pos> q = new LinkedList<>();
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isValid(nx, ny) && !visited[nx][ny] && (map[nx][ny] == 0 || map[nx][ny] == target)) {
                q.offer(new Pos(nx, ny, 1, i));
                visited[nx][ny] = true;
            }
        }

        while (!q.isEmpty()) {
            Pos now = q.poll();
            if (map[now.x][now.y] == target && now.d > 2)
                return now.d - 1;
            else if (map[now.x][now.y] == target && now.d <= 2) {
                continue;
            }
            int nx = now.x + dx[now.preD];
            int ny = now.y + dy[now.preD];

            if (isValid(nx, ny) && !visited[nx][ny] && (map[nx][ny] == 0 || map[nx][ny] == target)) {
                q.offer(new Pos(nx, ny, now.d + 1, now.preD));
                visited[nx][ny] = true;
            }
        }
        return -1; // 못찾을 경우
    }

    // 섬들간의 연결 조합 만들기
    private static void comb(int cnt, int start) {
        if (cnt == 2) {
            int minDist = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == input[0]) {
                        visited = new boolean[N][M];
                        int result = bfs(i, j, input[1]);
                        if (result != -1 && result > 1) {
                            minDist = Math.min(minDist, result);
                        }
                    }
                }
            }
            if (minDist != Integer.MAX_VALUE && minDist > 1)
                edgeList.add(new Edge(input[0], input[1], minDist));
            return;
        }
        for (int i = start; i <= islandCnt; i++) {
            input[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    // 맵 밖으로 나가는 지 확인
    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}