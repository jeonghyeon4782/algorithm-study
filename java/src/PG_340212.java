class PG_340212 {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int s = 1;
        int e = 0;
        for (int diff : diffs) {
            e = Math.max(e, diff);
        }

        while (s <= e) {
            int m = (s + e) / 2;    // 현재 레벨
            long allTime = 0;

            for (int i = 0; i < diffs.length; i++) {
                if (diffs[i] <= m) {
                    allTime += times[i];
                } else {
                    allTime += (long)(diffs[i] - m) * (times[i] + times[i - 1]) + times[i];
                }
            }

            if (allTime > limit) s = m + 1;
            else e = m - 1;
        }

        return s;
    }
}