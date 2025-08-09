import java.util.*;

class PG_118668 {
    public int solution(int alp, int cop, int[][] problems) {

        int maxAlp = 0;
        int maxCop = 0;

        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        int[][] dp = new int[maxAlp + 2][maxCop + 2];

        for (int r = 0; r <= maxAlp + 1; r++) {
            for (int c = 0; c <= maxCop + 1; c++) {
                dp[r][c] = Integer.MAX_VALUE;
            }
        }

        dp[alp][cop] = 0;

        for (int r = alp; r <= maxAlp; r++) {
            for (int c = cop; c <= maxCop; c++) {
                // 알고력 학습 하기
                dp[r + 1][c] = Math.min(dp[r + 1][c], dp[r][c] + 1);
                // 코딩력 학습 하기
                dp[r][c + 1] = Math.min(dp[r][c + 1], dp[r][c] + 1);
                // 문제를 풀어서 높이기
                for (int[] problem : problems) {
                    if (r >= problem[0] && c >= problem[1]) {
                        int nextAlp = Math.min(r + problem[2], maxAlp);
                        int nextCop = Math.min(c + problem[3], maxCop);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[r][c] + problem[4]);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}