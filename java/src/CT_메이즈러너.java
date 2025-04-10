import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class CT_메이즈러너 {
    // 좌표 클래스
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point [r=" + r + ", c=" + c + "]";
        }
    }

    // 정사각형 클래스
    static class Square implements Comparable<Square> {
        int sr, sc, er, ec;

        public Square(int sr, int sc, int er, int ec) {
            this.sr = sr;
            this.sc = sc;
            this.er = er;
            this.ec = ec;
        }

        @Override
        public int compareTo(Square o) {
            int now = Math.abs(this.sr - this.er);
            int next = Math.abs(o.sr - o.er);
            if (now != next)
                return Integer.compare(now, next); // 사각형의 크기가 작은 순
            if (this.sr != o.sr)
                return Integer.compare(this.sr, o.sr); // r이 작은 순
            return Integer.compare(this.sc, o.sc); // c가 작은 순;
        }

        @Override
        public String toString() {
            return "Square [sr=" + sr + ", sc=" + sc + ", er=" + er + ", ec=" + ec + "]";
        }
    }

    static int N, M, K; // 격자의 크기, 참가자 수, 게임 시간
    // 0 : 상, 1 : 하, 2 : 좌, 3 : 우
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };
    static Point exit; // 출구 좌표
    static int[][] map; // 미로
    static List<Point> manList; // 참가자 위치 리스트
    static List<Square> squareList; // 가능한 정사각형 리스트

    public static void main(String[] args) throws IOException {

        // 1. Setting
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        manList = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            manList.add(new Point(r, c));
        }
        st = new StringTokenizer(br.readLine());
        int pointR = Integer.parseInt(st.nextToken()) - 1;
        int pointC = Integer.parseInt(st.nextToken()) - 1;
        exit = new Point(pointR, pointC);
        int cnt = 0; // 참가자들의 이동 횟수

        // k번 만큼 반복
        for (int k = 0; k < K; k++) {

            // 2. 참가자 이동
            Iterator<Point> iterator = manList.iterator();
            while (iterator.hasNext()) {
                Point man = iterator.next();
                int realD = -1;
                int nowDist = Math.abs(exit.r - man.r) + Math.abs(exit.c - man.c);
                for (int d = 0; d < 4; d++) {
                    int nr = man.r + dr[d];
                    int nc = man.c + dc[d];
                    int dist = Math.abs(exit.r - nr) + Math.abs(exit.c - nc);
                    if (isValid(nr, nc) && nowDist > dist && map[nr][nc] == 0) {
                        nowDist = dist;
                        realD = d;
                    }
                }
                // 이동 가능할 경우 이동
                if (realD != -1) {
                    man.r += dr[realD];
                    man.c += dc[realD];
                    cnt++;
                }
                // 출구 도달 시 제거
                if (man.r == exit.r && man.c == exit.c) {
                    iterator.remove(); // Iterator로 안전하게 제거
                }
            }
            // 만약 참가자가 없다면 즉시 종료
            if (manList.isEmpty()) {
                System.out.println(cnt);
                System.out.println((exit.r + 1) + " " + (exit.c + 1));
                return;
            }

            // 3. 최적의 정사각형 찾기
            squareList = new ArrayList<>();
            for (int size = 1; size < N; size++) { // 정사각형의 한 변: 2~N
                for (int sr = 0; sr <= N - size - 1; sr++) {
                    for (int sc = 0; sc <= N - size - 1; sc++) {
                        int er = sr + size;
                        int ec = sc + size;

                        // 출구 포함 확인
                        if (!(exit.r >= sr && exit.r <= er && exit.c >= sc && exit.c <= ec)) continue;

                        // 참가자 포함 확인
                        boolean hasMan = false;
                        for (Point man : manList) {
                            if (man.r >= sr && man.r <= er && man.c >= sc && man.c <= ec) {
                                hasMan = true;
                                break;
                            }
                        }
                        if (!hasMan) continue;

                        squareList.add(new Square(sr, sc, er, ec));
                    }
                }
            }
            Collections.sort(squareList);

            // 4. 배열 돌리기
            int sr = squareList.get(0).sr;
            int er = squareList.get(0).er;
            int sc = squareList.get(0).sc;
            int ec = squareList.get(0).ec;
            int dist = er - sr; // 정사각형의 차이
            int[][] newMap = new int[dist + 1][dist + 1];
            // 미로 돌리기
            for (int r = 0; r < dist + 1; r++) {
                for (int c = 0; c < dist + 1; c++) {
                    newMap[r][c] = map[r + sr][c + sc];
                }
            }
            int[][] rotatedMap = new int[dist + 1][dist + 1];
            for (int r = 0; r < dist + 1; r++) {
                for (int c = 0; c < dist + 1; c++) {
                    rotatedMap[c][dist - r] = Math.max(0, newMap[r][c] - 1);
                }
            }
            for (int r = 0; r < dist + 1; r++) {
                for (int c = 0; c < dist + 1; c++) {
                    map[sr + r][sc + c] = rotatedMap[r][c];
                }
            }
            // 참가자 돌리기
            for (int i = 0; i < manList.size(); i++) {
                int nowR = manList.get(i).r;
                int nowC = manList.get(i).c;
                if (sr <= nowR && nowR <= er && sc <= nowC && nowC <= ec) {
                    int offsetR = nowR - sr;
                    int offsetC = nowC - sc;
                    int newR = offsetC;
                    int newC = dist - offsetR;
                    manList.get(i).r = sr + newR;
                    manList.get(i).c = sc + newC;
                }
            }
            // 출구 돌리기
            int exitNowR = exit.r;
            int exitNowC = exit.c;
            int offsetR = exitNowR - sr;
            int offsetC = exitNowC - sc;
            int newExitR = offsetC;
            int newExitC = dist - offsetR;
            exit.r = sr + newExitR;
            exit.c = sc + newExitC;
        }

        // 5. 출력
        System.out.println(cnt);
        System.out.println((exit.r + 1) + " " + (exit.c + 1));
    }

    ///////////////////////////////////////////////////////////////////////////

    // 미로를 출력하는 메서드
    private static void showMap() {
        System.out.println("미로 출력");
        for (int r = 0; r < N; r++) {
            System.out.println(Arrays.toString(map[r]));
        }
        System.out.println();
    }

    // 이동이 가능한 지 확인하는 메서드
    private static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}