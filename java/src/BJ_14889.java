import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_14889 {
    static int N;
    static boolean[] isSelected;
    static int[][] scores;
    static int answer = Integer.MAX_VALUE;
    static int aCnt;
    static int total;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isSelected = new boolean[N];
        scores = new int[N][N];
        for (int r = 0; r < N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                scores[r][c] = Integer.parseInt(st.nextToken());
                total += scores[r][c];
            }
        }
        subSet(0);
        System.out.println(answer);
    }

    private static void subSet(int cnt) {
        // 기저조건
        if (cnt == N) {
            if (aCnt == N / 2) {
                int aScore = 0;
                int bScore = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (isSelected[i] && isSelected[j]) {
                            aScore += scores[i][j];
                        }
                        if (!isSelected[i] && !isSelected[j]) {
                            bScore += scores[i][j];
                        }
                    }
                }
                answer = Math.min(Math.abs(aScore - bScore), answer);
            }
            return;
        }
        // 유도조건
        ++aCnt;
        isSelected[cnt] = true;
        subSet(cnt + 1);
        --aCnt;
        isSelected[cnt] = false;
        subSet(cnt + 1);
    }
}
