import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class CT_왕실의기사대결 {

    static class Warrior {
        int r, c, h, w, k, idx;

        public Warrior(int r, int c, int h, int w, int k, int idx) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Warrior [r=" + r + ", c=" + c + ", h=" + h + ", w=" + w + ", k=" + k + ", idx=" + idx + "]";
        }

    }

    static int L, N, Q; // 체스판 한 변의 길이, 전사의 수, 명령 수
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static int[][] map; // 체스판
    static int[][] warriorPos; // 현재 전사들의 위치
    static int[] damages;
    static Warrior[] warriors; // 전사들의 정보
    static List<Integer> moveWarriorIdx; // 이동하는 전사들의 인덱스
    static int answer = 0;

    public static void main(String[] args) throws IOException {

        // Setting
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        map = new int[L][L];
        warriorPos = new int[L][L];
        warriors = new Warrior[N + 1]; // idx는 1부터 N까지
        damages = new int[N + 1];
        moveWarriorIdx = new ArrayList<>();
        for (int r = 0; r < L; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < L; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            warriors[i] = new Warrior(r, c, h, w, k, i);
            for (int a = r; a < r + h; a++) {
                for (int b = c; b < c + w; b++) {
                    warriorPos[a][b] = i;
                }
            }
        }

        // 명령어 입력
        for (int q = 0; q < Q; q++) { // 현재 한번 임 나중에 변경
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()); // 이동시킬 기사의 인덱스
            int d = Integer.parseInt(st.nextToken()); // 이동 방향

            // 이동이 가능한 지 확인
            boolean b = isMove(idx, d);
            // 만약 이동이 불가능하다면 넘어가기
            if (!b)
                continue;
                // 만약 이동이 가능하다면?
            else {
                // 부딛히는 전사들 이동
                moveWarriorIdxSort(d);
                for (Integer i : moveWarriorIdx) {
                    move(i, d, false);
                }
                // 명령받은 전사 마지막 이동
                // 하지만 데미지를 주거나 정답에 추가 X
                move(idx, d, true);
            }
        }

        for (int damage : damages) {
            answer += damage;
        }
        System.out.println(answer);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void moveWarriorIdxSort(int d) {
        Collections.sort(moveWarriorIdx, (a, b) -> {
            Warrior wa = warriors[a];
            Warrior wb = warriors[b];

            switch (d) {
                case 0: // 위로 이동: r이 작은 순
                    return wa.r - wb.r;
                case 1: // 오른쪽 이동: c가 큰 순
                    return wb.c - wa.c;
                case 2: // 아래로 이동: r이 큰 순
                    return wb.r - wa.r;
                case 3: // 왼쪽 이동: c가 작은 순
                    return wa.c - wb.c;
                default:
                    return 0;
            }
        });
    }

    // 전사에게 데미지를 입히기
    private static void getDamage(int idx, int damage) {
        // 만약 idx가 damage를 받고 죽는다면 전사 죽이기
        if (warriors[idx].k - damage <= 0) {
            Warrior warrior = warriors[idx];
            for (int r = warrior.r; r < warrior.r + warrior.h; r++) {
                for (int c = warrior.c; c < warrior.c + warrior.w; c++) {
                    warriorPos[r][c] = 0;
                }
            }
            warriors[idx] = null;
            damages[idx] = 0;
        } else {
            warriors[idx].k = warriors[idx].k - damage;
            damages[idx] += damage;
        }

    }

    // 전사를 실제로 이동 후 데미지를 구해서 리턴하는 메서드
    private static void move(int idx, int d, boolean first) {
        Warrior warrior = warriors[idx];
        int damage = 0;

        // 기존 전사의 위치 배열을 0으로 만들기
        for (int r = warrior.r; r < warrior.r + warrior.h; r++) {
            for (int c = warrior.c; c < warrior.c + warrior.w; c++) {
                warriorPos[r][c] = 0;
            }
        }

        // 새로운 위치
        int nr = warrior.r + dr[d];
        int nc = warrior.c + dc[d];

        // 전사의 정보를 갱신
        warriors[idx].r = nr;
        warriors[idx].c = nc;
        warrior = warriors[idx];

        // 전사의 위치 배열을 갱신
        for (int r = warrior.r; r < warrior.r + warrior.h; r++) {
            for (int c = warrior.c; c < warrior.c + warrior.w; c++) {
                warriorPos[r][c] = warrior.idx;
                // 만약 함정이 있다면 데미지에 더하기
                if (map[r][c] == 1)
                    ++damage;
            }
        }

        if (damage != 0 && !first) {
            getDamage(idx, damage);
        }

    }

    private static boolean isMove(int idx, int d) {
        // 만약 idx 전사가 없다면 false 리턴
        if (warriors[idx] == null)
            return false;
        Queue<Integer> queue = new LinkedList<>();
        moveWarriorIdx = new ArrayList<Integer>(); // 자신을 제외한 움직이는 전사들의 인덱스 리스트
        queue.offer(idx);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            Warrior nowWarrior = warriors[now];
            Set<Integer> set = new HashSet<>();

            // 현재 전사의 직사각형 탐색
            for (int r = nowWarrior.r; r < nowWarrior.r + nowWarrior.h; r++) {
                for (int c = nowWarrior.c; c < nowWarrior.c + nowWarrior.w; c++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    // 만약 이동 중 벽이나 맵 밖으로 나간다면 false 리턴
                    if (!isValid(nr, nc) || map[nr][nc] == 2)
                        return false;
                    if (warriorPos[nr][nc] != nowWarrior.idx && warriorPos[nr][nc] != 0
                            && !moveWarriorIdx.contains(warriorPos[nr][nc]))
                        set.add(warriorPos[nr][nc]);
                }
            }
            // 이동하다가 부딛히는 전사들을 큐에 넣기
            for (Integer i : set) {
                queue.offer(i);
            }

            // 이동하다가 부딛히는 전사들을 이동하는 전사들의 인덱스 리스트에 추가하기
            for (Integer i : set) {
                moveWarriorIdx.add(i);
            }
        }

        return true;
    }

    // 맵 밖으로 나가는 지 확인하는 메서드
    private static boolean isValid(int r, int c) {
        return 0 <= r && r < L && 0 <= c && c < L;
    }

    // 체스판 출력하는 메서드
    private static void showMap() {
        System.out.println("=================== 체스판 출력 ===================");
        for (int i = 0; i < L; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }

    // 이동하는 전사들의 리스트를 출력하는 메서드 (자신은 제외)
    private static void showMoveWarriorIdx() {
        System.out.println("=================== 현재 이동하는 전사들의 리스트 ===================");
        for (Integer i : moveWarriorIdx) {
            System.out.println(warriors[i]);
        }
        System.out.println();
    }

    // 현재 전사들의 직사각형 위치를 출력하는 메서드
    private static void showWarriorPos() {
        System.out.println("=================== 현재 전사들의 직사각형 위치 ===================");
        for (int i = 0; i < L; i++) {
            System.out.println(Arrays.toString(warriorPos[i]));
        }
        System.out.println();
    }

    // 현재 전사들의 정보를 출력하는 메서드
    private static void showWarriors() {
        System.out.println("=================== 현재 전사들의 정보 ===================");
        System.out.println(Arrays.toString(warriors));
        System.out.println();
    }
}
