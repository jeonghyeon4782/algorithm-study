import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_17281 {
    static int[][] players;
    static int N;
    static boolean[] isSelected;
    static int[] input;
    static int maxScore;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        players = new int[N][9];
        isSelected = new boolean[9];
        input = new int[9];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 9; j++) {
                players[i][j] = sc.nextInt();
            }
        }
        permutation(1);
        System.out.println(maxScore);
    }

    public static void permutation(int cnt) {
        if (cnt == 9) {
            swap();
            Queue<Integer> q = new LinkedList<>();
            int out = 0;
            int inning = 0; // 1이닝
            int score = 0;  // 점수
            int now = 0;    // 현재 타자 순서
            while (out != N * 3) {
                inning = out / 3;
                int nowPlayer = input[now]; // 현재 플레이어 인덱스
                int nowHit = players[inning][nowPlayer];    // 현재 플레이어가 어떤 안타를 쳤는지

                // 큐를 활용해서 타자를 넣기
                switch (nowHit) {
                    case 0:
                        out++;
                        break;
                    case 1:
                        q.offer(1);
                        break;
                    case 2:
                        q.offer(1);
                        q.offer(0);
                        break;
                    case 3:
                        q.offer(1);
                        q.offer(0);
                        q.offer(0);
                        break;
                    case 4:
                        q.offer(1);
                        q.offer(0);
                        q.offer(0);
                        q.offer(0);
                        break;
                }
                if(out != 0 && out % 3 == 0) {
                    while(q.size() > 3) {
                        int c = q.poll();
                        if (c == 1) score++;
                    }
                    q = new LinkedList<>();
                }
                now = (now + 1) % 9;
            }
            maxScore = Math.max(maxScore, score);
            swap();
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (isSelected[i]) continue;
            input[cnt] = i;
            isSelected[i] = true;
            permutation(cnt + 1);
            isSelected[i] = false;
        }
    }

    public static void swap() {
        int temp = input[0];
        input[0] = input[3];
        input[3] = temp;
    }
}
