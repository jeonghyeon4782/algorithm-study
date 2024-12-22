import java.util.*;

class PG_340212 {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = Integer.MAX_VALUE;
        int s = Arrays.stream(diffs).min().orElse(Integer.MIN_VALUE);
        int e = Arrays.stream(diffs).max().orElse(Integer.MAX_VALUE);

        while (s <= e) {
            long sum = 0;
            int m = (s + e) / 2;

            for(int i = 0; i < diffs.length; i++) {
                if(i == 0) {
                    sum += solve(diffs[i], times[i], -1, limit, m);
                } else {
                    sum += solve(diffs[i], times[i], times[i - 1], limit, m);
                }
            }

            if(sum <= limit) {
                e = m - 1;
                answer = Math.min(answer, m);
            } else {
                s = m + 1;
            }
        }

        return answer;
    }

    public long solve(int diff, int time_cur, int time_prev, long limit, int level) {
        long answer = 0;

        if(time_prev == -1) {
            if(diff <= level) {
                answer += time_cur;
            } else {
                answer += (diff - level) * time_cur + time_cur;
            }
        } else {
            if(diff <= level) {
                answer += time_cur;
            } else {
                answer += (diff - level) * (time_cur + time_prev) + time_cur;
            }
        }

        return answer;
    }
}