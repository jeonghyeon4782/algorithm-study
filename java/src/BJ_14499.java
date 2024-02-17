import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14499 {

    // 0은 이동 X, 동, 서, 북, 남
    static int[] dx = { 0, 0, 0, -1, 1 };
    static int[] dy = { 0, 1, -1, 0, 0 };
    static int n, m, x, y, k; // 세로, 가로, 주사위 x, 주사위 y, 명령수
    static int nowX, nowY; // 현재 주사위의 위치
    static int[][] map;
    static int[][] dice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dice = new int[4][3]; // 주사위 도면 생성
        nowX = x;
        nowY = y;

        // 그래프 완성
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 명령어 실행
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int order = Integer.parseInt(st.nextToken());
            solve(order);
        }
    }

    public static void solve(int order) {
        int nx = nowX + dx[order];
        int ny = nowY + dy[order];

        // 주사위를 굴릴 수 없다면 함수 종료
        if (!isValid(nx, ny))
            return;
        else {
            //	주사위 굴리기
            switch (order) {
                case 1: right(); break;
                case 2: left(); break;
                case 3: up(); break;
                default: down(); break;
            }
            nowX = nx;
            nowY = ny;
        }

        // 이동한 위치가 0이면 주사위의 밑면을 바다에 복사
        if (map[nx][ny] == 0) {
            map[nx][ny] = dice[3][1];
        } else {	// 0이 아니면 값을 주사위 밑면에 복사후 0으로 초기화
            dice[3][1] = map[nx][ny];
            map[nx][ny] = 0;
        }
        System.out.println(dice[1][1]);
    }

    // 주사위를 굴릴 수 있는지 여부 확인
    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    // 위로 굴리기
    public static void up() {
        int temp = dice[0][1];
        for (int i  = 0; i < 3; i++) {
            dice[i][1] = dice[i + 1][1];
        }
        dice[3][1] = temp;
    }

    // 아래로 굴리기
    public static void down() {
        int temp = dice[3][1];
        for (int i  = 3; i > 0; i--) {
            dice[i][1] = dice[i - 1][1];
        }
        dice[0][1] = temp;
    }

    // 오른쪽으로 굴리기
    public static void right() {
        int temp = dice[1][0];
        dice[1][0] = dice[3][1];
        dice[3][1] = dice[1][2];
        dice[1][2] = dice[1][1];
        dice[1][1] = temp;
    }

    // 왼쪽으로 굴리기
    public static void left() {
        int temp = dice[1][2];
        dice[1][2] = dice[3][1];
        dice[3][1] = dice[1][0];
        dice[1][0] = dice[1][1];
        dice[1][1] = temp;
    }
}
