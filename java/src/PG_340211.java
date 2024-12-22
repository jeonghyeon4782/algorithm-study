import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PG_340211 {
    public static void main(String[] args) {
        int[][] points = {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}};
        int[][] routes = {{2, 3, 4, 5}, {1, 3, 4, 5}};

        solution(points, routes);
    }

    static class Robot implements Comparable<Robot> {
        int r, c, nextR, nextC, lastR, lastC, robotIdx, countIdx;

        public Robot(int r, int c, int nextR, int nextC, int lastR, int lastC, int robotIdx, int countIdx) {
            this.r = r;
            this.c = c;
            this.nextR = nextR;
            this.nextC = nextC;
            this.lastR = lastR;
            this.lastC = lastC;
            this.robotIdx = robotIdx;
            this.countIdx = countIdx;
        }

        public int compareTo(Robot o) {
            if (this.r != o.r) return Integer.compare(this.r, o.r);
            else return Integer.compare(this.c, o.c);
        }
    }

    public static int solution(int[][] points, int[][] routes) {
        int answer = 0; // 위험 발생 횟수
        int n = routes.length;
        List<Robot> robotList = new ArrayList<Robot>();
        for (int i = 0; i < n; i++) {
            int idx = routes[i][0];
            int nextIdx = routes[i][1];
            int lastIdx = routes[i][routes[i].length - 1];
            int r = points[idx - 1][0];
            int c = points[idx - 1][1];
            int nextR = points[nextIdx - 1][0];
            int nextC = points[nextIdx - 1][1];
            int lastR = points[lastIdx - 1][0];
            int lastC = points[lastIdx - 1][1];
            robotList.add(new Robot(r, c, nextR, nextC, lastR, lastC, i, 1));
        }
        answer += countEmergency(robotList);

        int time = 0;
        while(true) {
            ++time;

            // 모든 로봇들을 이동
            for(Robot robot : robotList) {
                moveRobot(robot);
            }

            // 위험 상황 발생 횟수 더하기
            answer += countEmergency(robotList);

            // next 변화, 도착 처리
            check(robotList, points, routes);

            if (robotList.isEmpty()) {
                System.out.println(answer);
                return answer;
            }
        }
    }

    // next와 last를 처리하는 메서드
    private static void check(List<Robot> robotList, int[][] points, int[][] routes) {
        Iterator<Robot> iterator = robotList.iterator();
        while (iterator.hasNext()) {
            Robot robot = iterator.next();
            if (robot.r == robot.lastR && robot.c == robot.lastC && robot.countIdx == routes[robot.robotIdx].length - 1) {
                iterator.remove(); // 안전하게 제거
            } else if (robot.r == robot.nextR && robot.c == robot.nextC) {
                int robotIdx = robot.robotIdx;
                int countIdx = robot.countIdx;
                int idx = routes[robotIdx][countIdx + 1];
                robot.nextR = points[idx - 1][0];
                robot.nextC = points[idx - 1][1];
                robot.countIdx++;
            }
        }
    }

    // 로봇을 이동
    private static void moveRobot(Robot robot) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int minR = robot.r;
        int minC = robot.c;
        int minDist = Math.abs(robot.r - robot.nextR) + Math.abs(robot.c - robot.nextC);

        for (int d = 0; d < 4; d++) {
            int nr = robot.r + dr[d];
            int nc = robot.c + dc[d];
            int dist = Math.abs(nr - robot.nextR) + Math.abs(nc - robot.nextC);

            if (dist < minDist) {
                minR = nr;
                minC = nc;
                minDist = dist;
            }
        }

        robot.r = minR;
        robot.c = minC;
    }

    // 위험 상황 발생 횟수 구하기 메서드
    private static int countEmergency(List<Robot> robotList) {
        int emergency = 0;
        Collections.sort(robotList);
        boolean flag = false;

        for (int i = 1; i < robotList.size(); i++) {
            Robot now = robotList.get(i);
            Robot prev = robotList.get(i - 1);
            if (now.r == prev.r && now.c == prev.c) {
                if (flag) continue;
                flag = true;
                ++emergency;
            } else {
                flag = false;
            }
        }
        return emergency;
    }
}
