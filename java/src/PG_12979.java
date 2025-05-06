class PG_12979 {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int start = 1;
        int range = 2 * w + 1;

        for (int now : stations) {
            int l = now - w;
            int r = now + w;
            if (start < l) {
                int count = l - start;
                answer += (count / range);
                if (count % range != 0) answer++;
            }
            start = r + 1;
        }

        if (start <= n) {
            int count = n - start + 1;
            answer += (count / range);
            if (count % range != 0) answer++;
        }

        return answer;
    }
}