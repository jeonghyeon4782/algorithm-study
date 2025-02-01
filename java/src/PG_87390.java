import java.util.*;

class PG_87390 {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int) (right - left + 1)];
        int idx = 0;

        for (long i = left; i <= right; i++) {
            long r = i / n;
            long c = i % n;

            if (r - c >= 0) {
                answer[idx++] = (int) r + 1;
            } else {
                answer[idx++] = (int) c + 1;
            }
        }

        return answer;
    }
}