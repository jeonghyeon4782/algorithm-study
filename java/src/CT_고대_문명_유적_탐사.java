import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CT_고대_문명_유적_탐사 {

    static class Point implements Comparable<Point> {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int compareTo(Point o) {
            if (this.c != o.c) return Integer.compare(this.c, o.c);
            return Integer.compare(o.r, this.r);
        }

        @Override
        public String toString() {
            return "Point{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static int K, M;    // K : 탐사 횟수, M : 유물 조각 수
    static int[][] map;
    static Queue<Integer> pieces;   // 유물 조각 리스트
    // 좌, 하, 우, 상
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        // 초기 세팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[5][5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        pieces = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            pieces.offer(Integer.parseInt(st.nextToken()));
        }

        // 총 t번 탐색
        for (int t = 0; t < K; t++) {
            // 어떤 곳으로 배열을 돌리지 정보
            int targetR = 0;
            int targetC = 0;
            int targetAngle = 0;
            int maxCnt = 0;
            int answer = 0;

            // 회전 타겟 구하기
            for (int angle = 1; angle <= 3; angle++) {
                for (int c = 1; c < 4; c++) {
                    for (int r = 1; r < 4; r++) {
                        int[][] copyMap = new int[5][5];
                        for (int i = 0; i < 5; i++) {
                            copyMap[i] = map[i].clone();
                        }
                        changerMap(copyMap, r, c, angle);
                        // 획득 가능한 유물 좌표 리스트
                        List<Point> treasureList = countTreasure(copyMap);
                        if (maxCnt < treasureList.size()) {
                            targetR = r;
                            targetC = c;
                            targetAngle = angle;
                            maxCnt = treasureList.size();
                        }
                    }
                }
            }
            
            // 맵을 회전
            changerMap(map, targetR, targetC, targetAngle);

            while (true) {
                // 획득 가능 유물 리스트 조회
                List<Point> treasureList = countTreasure(map);
                // 만약 더 이상 획득 할 수 없는 경우 멈추기
                if (treasureList.isEmpty()) break;
                answer += treasureList.size();
                Collections.sort(treasureList);
                for (Point p : treasureList) {
                    map[p.r][p.c] = pieces.poll();
                }
            }
            if (answer == 0) {
                return;
            }
            System.out.print(answer + " ");
        }
    }

    static boolean[][] visited;
    static List<Point> dfsList;
    
    // 맵에서 얻을 수 있는 유물의 좌표 리스트
    private static List<Point> countTreasure(int[][] map) {
        List<Point> answer = new ArrayList<>();
        visited = new boolean[5][5];

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (!visited[r][c]) {
                    visited[r][c] = true;
                    dfsList = new ArrayList<>();
                    dfsList.add(new Point(r, c));
                    dfs(r, c, map);
                    if (dfsList.size() >= 3) {
                        for (int i = 0; i < dfsList.size(); i++) {
                            answer.add(dfsList.get(i));
                        }
                    }
                }
            }
        }

        return answer;
    }

    private static void dfs(int r, int c, int[][] map) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isValid(nr, nc) && !visited[nr][nc] && map[r][c] == map[nr][nc]) {
                visited[nr][nc] = true;
                dfsList.add(new Point(nr, nc));
                dfs(nr, nc, map);
            }
        }
    }


    // 배열을 돌리는 메서드 (1 : 90도, 2 : 180도, 3 : 240도)
    private static void changerMap(int[][] map, int midR, int midC, int angle) {
        for (int t = 0; t < (angle * 2); t++) {
            // 오른쪽 상단 부터 시작
            int r = midR - 1;
            int c = midC + 1;
            int d = 0;
            int cnt = 0;
            int first = map[midR - 1][midC + 1];

            for (int i = 0; i < 8; i++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                map[r][c] = map[nr][nc];
                ++cnt;

                r = nr;
                c = nc;

                if (cnt == 2) {
                    cnt = 0;
                    d = (d + 1) % 4;
                }
            }
            map[midR][midC + 1] = first;
        }
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < 5 && 0 <= c && c < 5;
    }

    private static void showMap(int[][] map) {
        for (int i = 0; i < 5; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }
}
