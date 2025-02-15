import java.util.*;

class PG_340211 {

    class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    class Robot {
        int no, r, c, target;

        public Robot(int no, int r, int c, int target) {
            this.no = no;
            this.r = r;
            this.c = c;
            this.target = target;
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Point[] pointArr;    // 포인터의 좌표 배열 1 ~ n 까지
    static Queue<Robot> robots;  // 각 로봇의 현재 위치
    static int answer;

    public int solution(int[][] points, int[][] routes) {
        answer = 0;
        int n = points.length;  // 포인터의 갯수
        int m = routes.length;  // 로봇의 수
        pointArr = new Point[n + 1];
        robots = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            pointArr[i] = new Point(points[i - 1][0], points[i - 1][1]);
        }
        for (int i = 0; i < m; i++) {
            int nowPointIdx = routes[i][0];
            robots.offer(new Robot(i, pointArr[nowPointIdx].r, pointArr[nowPointIdx].c, 1));
        }

        answer += countAnswer();
        while (!robots.isEmpty()) {
            int size = robots.size();

            // 로봇들을 각각의 타겟을 향해서 이동
            for (int i = 0; i < size; i++) {
                Robot now = robots.poll();
                moveRobot(now, routes);
            }

            // 위험지역 더하기
            answer += countAnswer();

            // 타켓 변경 및 로봇 삭제
            for (int i = 0; i < size; i++) {
                Robot now = robots.poll();
                checkTarget(now, routes);
            }
        }

        return answer;
    }

    private void checkTarget(Robot robot, int[][] routes) {
        int target = routes[robot.no][robot.target];    // 몇 번 포인트로 가야하는지
        if (robot.r == pointArr[target].r && robot.c == pointArr[target].c) {
            if (robot.target + 1 >= routes[robot.no].length) {
                return;
            }
            robots.offer(new Robot(robot.no, robot.r, robot.c, robot.target + 1));
        } else {
            robots.offer(robot);
        }
    }

    private void moveRobot(Robot robot, int[][] routes) {
        int maxDist = Integer.MAX_VALUE;
        int target = routes[robot.no][robot.target];    // 어느 포인트로 가야하는지
        int r = -1;
        int c = -1;

        for (int d = 0; d < 4; d++) {
            int nr = robot.r + dr[d];
            int nc = robot.c + dc[d];
            int dist = Math.abs(pointArr[target].r - nr) + Math.abs(pointArr[target].c - nc);
            if (maxDist > dist) {
                maxDist = dist;
                r = nr;
                c = nc;
            }
        }
        robots.offer(new Robot(robot.no, r, c, robot.target));
    }

    // 충돌 갯수를 구하는 메서드
    private int countAnswer() {
        Map<String, Integer> map = new HashMap<>();

        for (Robot now : robots) {
            String p = String.valueOf(now.r) + " " + String.valueOf(now.c);
            if (map.containsKey(p)) {
                int cnt = map.get(p);
                map.put(p, cnt + 1);
            } else {
                map.put(p, 1);
            }
        }

        int answer = 0;

        for (String s : map.keySet()) {
            int cnt = map.get(s);
            if (cnt > 1) ++answer;
        }

        return answer;
    }
}