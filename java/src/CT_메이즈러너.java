import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CT_메이즈러너 {

    static class Point {
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    // 상하좌우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1 , 1};

    static int N, M, K;     // N : 격자의 크기, M : 참가자 수, K : 게임 시간
    static int[][] map;
    static List<Point> playerList;
    static Point exit;

    public static void main(String[] args) throws IOException {
        // setting
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        playerList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            playerList.add(new Point(r, c));
            map[r][c] = -1;
        }
        st = new StringTokenizer(br.readLine());
        exit = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        // 게임 시작
        int time = 0;
        while (time < 1) {
            // 1. 시간 증가
            ++time;

            // 2. 모든 참가자 이동
            int minDist = movePlayer();
            
            // 3. 게임 종료 여부 판단
            if (playerList.isEmpty()) {
                return;
            }
        }
    }

    // 회전 가능한 사각형 중 가장 상단 왼쪽에 있는 사각형을 탐색하는 메서드
    private static void searchRotateSquare(int minDist) {
        for (int i = minDist - 1; i < minDist + 1; i++) {
            if (isValid(exit.r - i, exit.c - i) && isValidSquare(exit.r - i, exit.r, exit.c - i, exit.c)) {
                RotateSquare(exit.r - i, exit.r, exit.c - i, exit.c);
                return;
            }
            if (isValid(exit.r - i, exit.c + i) && isValidSquare(exit.r - i, exit.r, exit.c, exit.c + i)) {
                // 처리 로직 추가
                return;
            }
            if (isValid(exit.r + i, exit.c - i) && isValidSquare(exit.r, exit.r + i, exit.c - i, exit.c)) {
                // 처리 로직 추가
                return;
            }
            if (isValid(exit.r + i, exit.c + i) && isValidSquare(exit.r, exit.r + i, exit.c, exit.c + i)) {
                // 처리 로직 추가
                return;
            }
        }
    }

    // 맵을 90도 회전시키는 메서드
    private static void RotateSquare(int startR, int endR, int startC, int endC) {
        
    }

    // 사각형 내에 플레이어가 있는지 확인하는 메서드
    private static boolean isValidSquare(int startR, int endR, int startC, int endC) {
        for (Point player : playerList) {
            if (startR <= player.r && player.r <= endR && startC <= player.c && player.c <= endC) {
                return true;
            }
        }
        return false;
    }


    // 참가자들을 모두 이동시키는 메서드
    private static int movePlayer() {
        // 참가자 이동
        int answer = Integer.MAX_VALUE;
        Iterator<Point> list = playerList.iterator();
        while (list.hasNext()) {
            Point player = list.next();
            int changeR = -1;
            int changeC = -1;
            int minDist = Math.abs(player.r - exit.r) + Math.abs(player.c - exit.c);

            for (int d = 0; d < 4; d++) {
                int nr = player.r + dr[d];
                int nc = player.c + dc[d];

                if (isValid(nr, nc) && map[nr][nc] == 0) {
                    int dist = Math.abs(nr - exit.r) + Math.abs(nc - exit.c);
                    if (dist < minDist) {
                        changeR = nr;
                        changeC = nc;
                        minDist = dist;
                    }
                }
            }
            
            // 만약 이동을 했다면 플레이어를 적절한 곳에 이동처리
            if (changeR != -1 && changeC != -1) {
                player.r = changeR;
                player.c = changeC;
            }

            int changeDist = Math.min(answer, Math.abs(player.r - exit.r) + Math.abs(player.c - exit.c));
            if (changeDist != 0) {
                // 플레이어 이동 후 거리 중 최단 거리를 구한다.
                answer = Math.min(answer, changeDist);
            } else {
                // 만약 이동 후 거리가 0이면 플레이어를 삭제한다.
                list.remove();
            }
        }

        // 메서드 처리 후 모든 플레어어 중 출구와 가장 가까운 플레어이의 최단거리를 리턴
        return answer;
    }

    // 맵 밖으로 나가는 지 확인하는 메서드
    private static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    // 현재 맵을 보여주는 메서드
    private static void showMap() {
        System.out.println("현재 맵의 상태");
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    // 현재 플레이어들의 위치를 보여주는 메서드
    private static void showPlayerList() {
        System.out.println("현재 플레이어들의 위치");
        for (Point player : playerList) {
            System.out.println(player.r + " " + player.c);
        }
        System.out.println();
    }
    
    // 현재 출구의 위치를 보여주는 메서드
    private static void showExit() {
        System.out.println("현재 출구의 위치");
        System.out.println(exit.r + " " + exit.c);
        System.out.println();
    }
}
