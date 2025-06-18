import java.util.*;

class PG_12971 {

    int[] dp1;  // 첫 번째 스티커 선택 O
    int[] dp2;  // 첫 번째 스티커 선택 X
    int N;

    public int solution(int sticker[]) {
        int answer = 0;
        N = sticker.length;

        if (N <= 3) {
            for (int i = 0; i < N; i++) {
                answer = Math.max(sticker[i], answer);
            }
            return answer;
        }

        dp1 = new int[N + 1];
        dp1[1] = sticker[0];
        dp2 = new int[N + 1];

        for (int i = 1; i < N - 1; i++) {
            dp1[i + 1] = Math.max(dp1[i], dp1[i - 1] + sticker[i]);
        }

        for (int i = 1; i < N; i++) {
            dp2[i + 1] = Math.max(dp2[i], dp2[i - 1] + sticker[i]);
        }

        answer = Math.max(dp1[N - 1], dp2[N]);

        return answer;
    }
}