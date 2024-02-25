import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_21610 {
    static class Cloud {
        int r, c;

        public Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[] diagonal = {2, 4, 6, 8}; // 대각선의 인덱스 번호
    static int N, M;    // N : 가로,세로 길이     M : 이동 횟수
    static int[][] water;   // 물의 양을 저장하는 배열
    static Queue<Cloud> clouds; // 이동할 구름들
    static boolean[][] visited; // 이동을 마친 구름들의 좌표

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        water = new int[N][N];
        clouds = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                water[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 최초 구름 생성
        for (int i = N - 2; i < N; i++) for (int j = 0; j < 2; j++) clouds.offer(new Cloud(i, j));
        // 명령 시작
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            solve(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.println(sumCloud());
    }

    private static int sumCloud() {
        return Arrays.stream(water)
                .flatMapToInt(Arrays::stream)
                .sum();
    }

    // 구름 이동시켜서 비를 내리게 하고 구름을 없애는 메서드
    private static void solve(int d, int s) {   // d : 방향, s : 거리
        visited = new boolean[N][N];
        while (!clouds.isEmpty()) {
            Cloud nowCloud = clouds.poll();
            int nr = (nowCloud.r + N + dr[d] * (s % N)) % N;
            int nc = (nowCloud.c + N + dc[d] * (s % N)) % N;
            visited[nr][nc] = true;  // 이동한 곳의 좌표를 방문처리
            water[nr][nc] += 1; // 이동한 곳 비내리기
        }

        // 이동한 구름들의 대각선을 확인 후 비내리게 하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    for (int idx : diagonal) {
                        int nr = i + dr[idx];
                        int nc = j + dc[idx];
                        if (isValid(nr, nc) && water[nr][nc] != 0) ++water[i][j];
                    }
                }
            }
        }

        // 구름 생성하기
        makeCloud();
    }

    // 구름 생성하는 메서드
    private static void makeCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && water[i][j] >= 2) {
                    clouds.offer(new Cloud(i, j));
                    water[i][j] = Math.max((water[i][j] - 2), 0);
                }
            }
        }
    }

    // 맵을 벗어났는지 여부 체크
    private static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
