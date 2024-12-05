import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CT_메두사와전사들 {

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int N, M;    // N : 마을의 크기, M : 전사의 수
    static Point medusa;    // 현재 메두사의 위치
    static Point park;
    static int[][] map;     // 도로 : 0, 도로X : 1, 전사 : 2
    static boolean[][] visited;     // 메두사의 시선, dfs, bfs 활용
    static boolean isConnected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][N];
        map = new int[N][N];
        st = new StringTokenizer(br.readLine());
        medusa = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        park = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 2;
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 공원을 갈 수 있는 지 여부 확인
        visited = new boolean[N][N];
        dfs(medusa.r, medusa.c);
        if (!isConnected) {
            System.out.println(-1);
            return;
        }

    }

    // 메두사의 이동 및 전사 확인
    public static void moveMedusa() {
        
        // 메두사의 이동
        int minDist = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int d = 0; d < 4; d++) {
            int nr = medusa.r + dr[d];
            int nc = medusa.c + dc[d];
            if (isValid(nr, nc) && map[nr][nc] != 1) {
                int dist = bfs(nr, nc, park.r, park.c);
                if (dist < minDist) {
                    minDist = dist;
                    minIdx = d;
                }
            }
        }
        medusa.r += dr[minIdx];
        medusa.c += dc[minIdx];

        // 전사가 존재하면 죽이기
        if(map[medusa.r][medusa.c] == 2) {
            map[medusa.r][medusa.c] = 0;
        }
    }
    
    // 최단 거리 리턴 함수
    public static int bfs(int r, int c, int targetR, int targetC) {
        visited = new boolean[N][N];
        visited[r][c] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c, 0});

        while(!q.isEmpty()) {
            int[] now = q.poll();
            if (now[0] == targetR && now[1] == targetC) return now[2];
            for (int d = 0; d < 4; d++) {
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                if (isValid(nr, nc) && map[nr][nc] != 1 && !visited[nr][nc]) {
                    q.offer(new int[] {nr, nc, now[2] + 1});
                    visited[nr][nc] = true;
                }
            }
        }
        return -1;
    }

    // 메두사와 공원의 연결 여부 확인
    public static void dfs(int r, int c) {

        visited[r][c] = true;

        if (r == park.r && c == park.c) {
            isConnected = true;
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (isValid(nr, nc) && !visited[nr][nc]) {
                dfs(nr, nc);
            }
        }
    }

    public static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
