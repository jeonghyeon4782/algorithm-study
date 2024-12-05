import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CT_고대문명유적탐사 {

    static class Point implements Comparable<Point> {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int compareTo(Point o) {
            if (this.c != o.c) return Integer.compare(this.c, o.c);
            else return Integer.compare(o.r, this.r);
        }
    }

    // 북, 서, 남, 동
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int K, M;
    static int[][] map;
    static int[][] map_copy;
    static boolean[][] visited;
    static List<Point> zeroChangeList;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        // setting
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[5][5];
        map_copy = new int[5][5];
        q = new LinkedList<Integer>();
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            q.offer(Integer.parseInt(st.nextToken()));
        }

        // K번 탐사 시작
        for (int t = 0; t < K; t++) {

            // 어느 방향이 제일 적절한 지 찾기
            int changeR = 0;
            int changeC = 0;
            int changeAngle = 0;
            int maxValue = Integer.MIN_VALUE;
            for (int angle = 0; angle < 3; angle++) {
                for (int c = 1; c < 4; c++) {
                    for (int r = 1; r < 4; r++) {
                        changeArray(r, c, angle);
                        visited = new boolean[5][5];
                        int sumValue = 0;
                        for (int i = 0; i < 5; i++) {
                            for (int j = 0; j < 5; j++) {
                                if (!visited[i][j]) {
                                    int cnt = bfs(i, j);
                                    if (cnt >= 3) sumValue += cnt;
                                }
                            }
                        }
                        if (sumValue > maxValue) {
                            maxValue = sumValue;
                            changeR = r;
                            changeC = c;
                            changeAngle = angle;
                        }
                    }
                }
            }

            if (maxValue <= 0) {
                return;
            }

            // 실제 배열을 돌리기
            changeArray(changeR, changeC, changeAngle);
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    map[i][j] = map_copy[i][j];
                }
            }

            // 조각이 모이는 곳을 새로운 조각으로 채워 넣기
            int answer = 0;
            while (true) {
                visited = new boolean[5][5];
                zeroChangeList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (!visited[i][j]) {
                            List<Point> cntList = searchChanged(i, j);
                            if (cntList.size() >= 3) {
                                for (Point p : cntList) {
                                    zeroChangeList.add(p);
                                }
                            }
                        }
                    }
                }

                if (zeroChangeList.size() == 0) break;

                answer += zeroChangeList.size();

                Collections.sort(zeroChangeList);
                for (Point p : zeroChangeList) {
                    int num = q.poll();
                    map[p.r][p.c] = num;
                }

            }

            System.out.print(answer + " ");
        }

    }

    private static List<Point> searchChanged(int r, int c) {
        int cntValue = 1;
        Queue<int[]> queue = new LinkedList<>();
        List<Point> answer = new ArrayList<>();
        visited[r][c] = true;
        queue.offer(new int[]{r, c});
        answer.add(new Point(r, c));
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                if (isValidMap(nr, nc) && !visited[nr][nc] && (map[r][c] == map[nr][nc])) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                    answer.add(new Point(nr, nc));
                    cntValue++;
                }
            }
        }
        return answer;
    }

    private static int bfs(int r, int c) {
        int cntValue = 1;
        Queue<int[]> queue = new LinkedList<>();
        visited[r][c] = true;
        queue.offer(new int[]{r, c});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now[0] + dr[d];
                int nc = now[1] + dc[d];
                if (isValidMap(nr, nc) && !visited[nr][nc] && (map_copy[r][c] == map_copy[nr][nc])) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                    cntValue++;
                }
            }
        }
        return cntValue;
    }

    // 배열 돌리기 메서드(0 : 90도, 1 : 180도, 2 : 240도)
    private static void changeArray(int r, int c, int angle) {
        copyArray();
        int cnt = 0;    // 돌려야 하는 횟수
        if (angle == 0) cnt = 2;
        else if (angle == 1) cnt = 4;
        else if (angle == 2) cnt = 6;

        for (int i = 0; i < cnt; i++) {
            int d = 0;
            int sr = r + 1;
            int sc = c + 1;
            int startValue = map_copy[sr][sc];

            for (int j = 0; j < 8; j++) {
                int nr = sr + dr[d];
                int nc = sc + dc[d];
                if (!isValid(r, c, nr, nc)) {
                    d = (d + 1) % 4;
                    nr = sr + dr[d];
                    nc = sc + dc[d];
                }
                map_copy[sr][sc] = map_copy[nr][nc];
                if (j == 7) {
                    map_copy[sr][sc] = startValue;
                    break;
                }
                sr = nr;
                sc = nc;
            }

        }

    }

    // 배열 복사본에 진짜 배열을 복사 메서드
    private static void copyArray() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                map_copy[i][j] = map[i][j];
            }
        }
    }

    private static boolean isValid(int r, int c, int nr, int nc) {
        if (Math.abs(r - nr) + Math.abs(c - nc) > 2) return false;
        return true;
    }

    private static boolean isValidMap(int r, int c) {
        return 0 <= r && r < 5 && 0 <= c && c < 5;
    }
}
