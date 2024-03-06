import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_2115 {
    static class Honey {
        int startR, startC, endR, endC, maxHoney;
        public Honey(int startR, int startC, int endR, int endC, int maxHoney) {
            this.startR = startR;
            this.startC = startC;
            this.endR = endR;
            this.endC = endC;
            this.maxHoney = maxHoney;
        }
    }

    static int N, M, C, maxHoney, result;
    static int[][] map;
    static boolean[] isSelected;
    static int[] nums;
    static List<Honey> honeyList;
    static Honey[] honeys;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int t = 1; t <= test; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            isSelected = new boolean[M];
            map = new int[N][N];
            nums = new int[M];
            honeyList = new ArrayList<>();
            honeys = new Honey[2];
            result = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N - M + 1; j++) {
                    for (int k = 0; k < M; k++) {
                        maxHoney = 0;
                        nums[k] = map[i][j + k];
                    }
                    subSet(0);
                    honeyList.add(new Honey(i, j, i, j + M - 1, maxHoney));
                }
            }
            comb(0, 0);
            System.out.printf("#%d %d\n", t, result);
        }
    }

    private static void comb(int cnt, int start) {
        if (cnt == 2) {
            Honey honey1 = honeys[0];
            Honey honey2 = honeys[1];
            if (honey1.startR == honey2.startR && honey1.endC >= honey2.startC) return;
            result = Math.max(result, honey1.maxHoney + honey2.maxHoney);
            return;
        }

        for (int i = start; i < honeyList.size(); i++) {
            honeys[cnt] = honeyList.get(i);
            comb(cnt + 1, i + 1);
        }
    }

    private static void subSet(int cnt) {
        if (cnt == M) {
            int sumHoney = 0;
            int sumHoney2 = 0;
            for (int i = 0; i < M; i++) {
                if (isSelected[i]) {
                    sumHoney += (nums[i] * nums[i]);
                    sumHoney2 += nums[i];
                }
            }
            if (sumHoney > maxHoney && sumHoney2 <= C) {
                maxHoney = sumHoney;
            }
            return;
        }

        isSelected[cnt] = true;
        subSet(cnt + 1);
        isSelected[cnt] = false;
        subSet(cnt + 1);
    }
}
