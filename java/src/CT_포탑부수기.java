import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class CT_포탑부수기 {

    // Point 클래스
    static class Point {
        int r, c;
        List<Point> route;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public Point(int r, int c, List<Point> route) {
            this.r = r;
            this.c = c;
            this.route = route;
        }

        @Override
        public String toString() {
            return "Point [r=" + r + ", c=" + c + "]";
        }
    }

    // 포탑 클래스
    static class Turret {
        int r, c, power, time; // 좌표, 공격력, 최근 공격 시간

        public Turret(int r, int c, int power, int time) {
            this.r = r;
            this.c = c;
            this.power = power;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Turret [r=" + r + ", c=" + c + ", power=" + power + ", time=" + time + "]";
        }
    }

    static int N, M, K; // N * M, K : 공격 시간
    // 우 하 좌 상 왼쪽위 오른쪽위 왼쪽아래 오른쪽아래
    static int[] dr = {0, 1, 0, -1, -1, -1, 1, 1};
    static int[] dc = {1, 0, -1, 0, -1, 1, -1, 1};
    static int[][] map; // 포탑들의 공격력 격자
    static List<Turret> turretList; // 포탑 리스트

    public static void main(String[] args) throws IOException {

        // 1. setting
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        turretList = new ArrayList<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] != 0) {
                    turretList.add(new Turret(r, c, map[r][c], 0));
                }
            }
        }
//		System.out.println("공격 시작 전 포탑 상황 : " + turretList);
//		showMap();
        // K번 공격 시작
        for (int time = 1; time <= K; time++) {
//			System.out.println(time + " 턴 시작 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//			System.out.println();
            // 2. 공격자 선정
            sortAttckTurret();
            Turret attackTurret = turretList.get(0);
            turretList.get(0).power += (N + M);
            turretList.get(0).time = time;
            map[attackTurret.r][attackTurret.c] += (N + M);
//			System.out.println("공격자 : " + attackTurret);

            // 3. 공격 대상 선정
            sortTargetTurret(attackTurret.r, attackTurret.c);
            Turret targetTurret = turretList.get(0);
//			System.out.println("공격 대상 : " + targetTurret);

            // 4. 공격 (레이저 공격 or 포탄 공격)
            List<Point> route = attack(attackTurret.r, attackTurret.c, targetTurret.r, targetTurret.c);
            if (route != null) {
                // 레이저 공격
//				System.out.println("레이저 공격 가능");
                int attackPower = map[attackTurret.r][attackTurret.c];
                decreasePower(targetTurret.r, targetTurret.c, attackPower);
                for (Point p : route) {
                    decreasePower(p.r, p.c, attackPower / 2);
                }
//				System.out.println("공격 후 터렛 정보 : " + turretList);
//				showMap();
                // 만약 터렛이 하나만 남는다면 끝내기
                if (turretList.size() == 1) {
                    showAnswer();
                    return;
                }
                // 5. 회복 (공격도 안하고, 안당하고, 피해도 안입은 터렛만)
                boolean[][] recover = new boolean[N][M];
                for (Point p : route) {
                    recover[p.r][p.c] = true;
                }
                recover[attackTurret.r][attackTurret.c] = true;
                recover[targetTurret.r][targetTurret.c] = true;
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        if (!recover[r][c] && map[r][c] != 0) {
                            increasePower(r, c);
                        }
                    }
                }
            } else {
                // 포탄 공격
//				System.out.println("포탄 공격 가능");
                boolean[][] recover = new boolean[N][M];
                int attackPower = map[attackTurret.r][attackTurret.c];
                decreasePower(targetTurret.r, targetTurret.c, attackPower);
                for (int d = 0; d < 8; d++) {
                    int nr = targetTurret.r + dr[d];
                    int nc = targetTurret.c + dc[d];
                    if (!isValid(nr, nc)) {
                        Point point = changePoint(nr, nc);
                        nr = point.r;
                        nc = point.c;
                    }
                    // 만약 터렛이 없으면 넘어가기
                    if (map[nr][nc] == 0)
                        continue;
                    // 만약 공격한 터렛까지 퍼지면 넘어가기
                    if (nr == attackTurret.r && nc == attackTurret.c)
                        continue;
                    recover[nr][nc] = true;
                    decreasePower(nr, nc, attackPower / 2);
                }
//				System.out.println("공격 후 터렛 정보 : " + turretList);
//				showMap();
                // 만약 터렛이 하나만 남는다면 끝내기
                if (turretList.size() == 1) {
                    showAnswer();
                    return;
                }
                // 5. 회복
                recover[attackTurret.r][attackTurret.c] = true;
                recover[targetTurret.r][targetTurret.c] = true;
                for (int r = 0; r < N; r++) {
                    for (int c = 0; c < M; c++) {
                        if (!recover[r][c] && map[r][c] != 0) {
                            increasePower(r, c);
                        }
                    }
                }
//				System.out.println("회복 후 터렛 정보 : " + turretList);
//				showMap();
            }
        }
        showAnswer();
    }

    /////////////////////////////////////////////////////////////////////////////////

    // 가장 강한 포탑의 공격력을 출력하는 메서드
    private static void showAnswer() {
        int maxPower = -1;
        ListIterator<Turret> iterator = turretList.listIterator();
        while (iterator.hasNext()) {
            Turret now = iterator.next();
            maxPower = Math.max(maxPower, now.power);
        }
        System.out.println(maxPower);
    }

    // 터렛의 공격력을 높이는 메서드
    private static void increasePower(int r, int c) {
        // 포탑 격자 높이기
        map[r][c] += 1;

        // 포탑 리스트 높이기
        ListIterator<Turret> iterator = turretList.listIterator();
        while (iterator.hasNext()) {
            Turret now = iterator.next();
            if (now.r == r && now.c == c) {
                // 포탑의 공격력 높이기
                now.power += 1;
                iterator.set(now);
                break;
            }
        }
    }

    // 터렛의 공격력을 줄이는 메서드
    private static void decreasePower(int r, int c, int attackPower) {
        // 포탑 격자 줄이기
        map[r][c] = Math.max(0, map[r][c] - attackPower);

        // 포탑 리스트 줄이기
        ListIterator<Turret> iterator = turretList.listIterator();
        while (iterator.hasNext()) {
            Turret now = iterator.next();
            if (now.r == r && now.c == c) {
                // 포탑의 공격력이 0이라면 없애기
                if (map[now.r][now.c] == 0) {
                    iterator.remove();
                } else {
                    now.power -= attackPower;
                    iterator.set(now);
                }
                break;
            }
        }
    }

    // 레이저 공격하는 메서드
    private static List<Point> attack(int r, int c, int endR, int endC) {
        boolean[][] visited = new boolean[N][M];
        visited[r][c] = true;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(r, c, new ArrayList<>()));
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];
                // 만약 맵 밖으로 나간다면?
                if (!isValid(nr, nc)) {
                    Point point = changePoint(nr, nc);
                    nr = point.r;
                    nc = point.c;
                }
                if (!visited[nr][nc] && map[nr][nc] != 0) {
                    if (nr == endR && nc == endC) {
                        return now.route;
                    }
                    visited[nr][nc] = true;
                    List<Point> nowRoute = new ArrayList<>();
                    nowRoute.addAll(now.route);
                    nowRoute.add(new Point(nr, nc));
                    q.offer(new Point(nr, nc, nowRoute));
                }
            }
        }
        return null;
    }

    // 맵 밖으로 나간 좌표를 바꿔주는 메서드
    private static Point changePoint(int r, int c) {
        if (r < 0) {
            r = N - 1;
        } else if (r >= N) {
            r = 0;
        }
        if (c < 0) {
            c = M - 1;
        } else if (c >= M) {
            c = 0;
        }
        return new Point(r, c);
    }

    // 맵 밖으로 나가는 지 여부 확인 메서드
    private static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    // 맵을 보여주는 메서드
    private static void showMap() {
        System.out.println("====================== 현재 격자의 상태 ======================");
        for (int r = 0; r < N; r++) {
            System.out.println(Arrays.toString(map[r]));
        }
        System.out.println();
    }

    // 공격자를 찾기 위해 정렬하는 메서드
    private static void sortAttckTurret() {
        Collections.sort(turretList, (a, b) -> {
            // 공격력이 낮은 순
            if (a.power != b.power)
                return Integer.compare(a.power, b.power);
            // 최근에 공격한 순
            if (a.time != b.time)
                return Integer.compare(b.time, a.time);
            // r + c가 큰 순
            if (a.r + a.c != b.r + b.c)
                return Integer.compare(b.r + b.c, a.r + a.c);
            // c가 큰 순
            return Integer.compare(b.c, a.c);
        });
    }

    // 공격 대상을 찾기 위해 정렬하는 메서드
    private static void sortTargetTurret(int r, int c) {
        Collections.sort(turretList, (a, b) -> {
            // 공격자는 항상 마지막으로
            if (a.r == r && a.c == c)
                return 1;
            if (b.r == r && b.c == c)
                return -1;
            // 공격력이 낮은 순
            if (a.power != b.power)
                return Integer.compare(b.power, a.power);
            // 최근에 공격한 순
            if (a.time != b.time)
                return Integer.compare(a.time, b.time);
            // r + c가 큰 순
            if (a.r + a.c != b.r + b.c)
                return Integer.compare(a.r + a.c, b.r + b.c);
            // c가 큰 순
            return Integer.compare(a.c, b.c);
        });
    }
}
