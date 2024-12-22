import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_20055 {
    static int N, K; // N : 2N은 컨베이어 벨트의 길이, K : 내구도 0인 칸이 K 이상이면 종료
    static int[] belt;  // 컨베이어 벨트의 내구도
    static boolean[] robots;    // 그 칸에 로봇이 존재하는지 여부 확인
    static int zeroCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        robots = new boolean[2 * N];
        belt = new int[2 * N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            int durability = Integer.parseInt(st.nextToken());
            belt[i] = durability;
            if (belt[i] == 0) zeroCnt++;
        }

        int time = 0;
        while (true) {
            ++time;
            rotate();
            move();
            addRobot();
            if (solve()) {
                System.out.println(time);
                return;
            }
        }
    }

    private static boolean solve() {
        return zeroCnt >= K;
    }

    private static void addRobot() {
        if (belt[0] > 0 && !robots[0]) {
            robots[0] = true;
            belt[0]--;
            if (belt[0] == 0) zeroCnt++;
        }
    }

    private static void move() {
        for (int i = N - 2; i >= 0; i--) { // N-2부터 시작
            if (robots[i] && !robots[i + 1] && belt[i + 1] > 0) {
                robots[i + 1] = true;
                robots[i] = false;
                belt[i + 1]--;
                if (belt[i + 1] == 0) zeroCnt++;
            }
        }
        robots[N - 1] = false; // 내리는 위치에서 로봇 제거
    }

    private static void rotate() {
        int lastBelt = belt[2 * N - 1];
        boolean lastRobot = robots[2 * N - 1];
        for (int i = 2 * N - 2; i >= 0; i--) {
            belt[i + 1] = belt[i];
            robots[i + 1] = robots[i];
        }
        belt[0] = lastBelt;
        robots[0] = lastRobot;

        robots[N - 1] = false; // 내리는 위치에서 로봇 제거
    }
}
