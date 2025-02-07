import java.io.*;
import java.util.*;

public class ST_7721 {

    static List<int[]> matches;
    static int[] input, F, scores;
    static double ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        matches = new ArrayList<>();
        input = new int[2];
        F = new int[4];
        scores = new int[4];
        for (int i = 0; i < 4; i++) {
            F[i] = Integer.parseInt(st.nextToken());
        }
        comb(0, 0);
        solve(0, 1.0);
        System.out.printf("%.3f", ans * 100);
    }

    public static void solve(int depth, double total) {
        if (depth == matches.size()) {
            if (check()) {
                ans += total;
            }
            return;
        }

        int[] match = matches.get(depth);
        int first = match[0];
        int second = match[1];
        double[] probabilityArr = getProbability(F[first], F[second]);

        // A 승리 시
        scores[first] += 3;
        solve(depth + 1, total * probabilityArr[0]);
        scores[first] -= 3;

        // 무승부
        scores[first] += 1;
        scores[second] += 1;
        solve(depth + 1, total * probabilityArr[1]);
        scores[first] -= 1;
        scores[second] -= 1;

        // B 승리 시
        scores[second] += 3;
        solve(depth + 1, total * probabilityArr[2]);
        scores[second] -= 3;
    }

    public static boolean check() {
        int firstScore = scores[0];  // 첫 번째 루돌프의 승점
        int rank = 1;

        for (int i = 1; i < 4; i++) {
            if (firstScore < scores[i]) rank++;
        }

        if (rank <= 2) return true;
        return false;
    }

    public static double[] getProbability(int F1, int F2) {
        double[] ans = new double[3];

        ans[0] = (4.0 * F1) / ((5.0 * F1) + (5.0 * F2));
        ans[2] = (4.0 * F2) / ((5.0 * F1) + (5.0 * F2));
        ans[1] = (F1 + F2) / ((5.0 * F1) + (5.0 * F2));

        return ans;
    }

    public static void comb(int cnt, int start) {

        if (cnt == 2) {
            matches.add(new int[] {input[0], input[1]});
            return;
        }

        for (int i = start; i < 4; i++) {
            input[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }
}