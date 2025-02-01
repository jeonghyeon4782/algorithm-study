public class PG_92342 {

    static int[] Apeach, Ryan;
    static int N;
    static boolean flag;
    static int maxDist;
    static int[] answer;

    public int[] solution(int n, int[] info) {
        N = n;
        Apeach = info.clone();
        Ryan = new int[11];

        comb(0, n);

        if (!flag) return new int[] {-1};

        return answer;
    }

    private void comb(int cnt, int arrow) {

        if (cnt == 10) {
            Ryan[cnt] = arrow;
            int apeachScore = 0;
            int ryanScore = 0;
            for (int i = 0; i < 11; i++) {
                if (Ryan[i] == 0 && Apeach[i] == 0) continue;
                if (Ryan[i] > Apeach[i]) ryanScore += (10 - i);
                else apeachScore += 10 - i;
            }

            if (apeachScore >= ryanScore) return;

            if (maxDist < (ryanScore - apeachScore)) {
                maxDist = ryanScore - apeachScore;
                answer = Ryan.clone();
                flag = true;
            }

            else if (maxDist == (ryanScore - apeachScore)) {
                for (int i = 10; i >= 0; i--) {
                    if (Ryan[i] > answer[i]) {
                        answer = Ryan.clone();
                        break;
                    } else if (Ryan[i] < answer[i]) {
                        break;
                    }
                }
            }
            return;
        }

        for (int i = 0; i <= arrow; i++) {
            Ryan[cnt] = i;
            comb(cnt + 1, arrow - i);
        }
    }
}