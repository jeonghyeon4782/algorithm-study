import java.util.*;

class PG_64062 {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int s = 0;
        int e = 200000000;

        while (s <= e) {
            int m = (s + e) / 2;
            // 만약 m명이 건널 수 있다면?
            if (check(stones, m, k)) {
                answer = m;
                s = m + 1;
            }
            // 만약 m명이 건널 수 없다면?
            else {
                e = m - 1;
            }
        }

        return answer;
    }

    public boolean check(int[] stones, int m, int k) {
        int skip = 0;
        for (int stone : stones) {
            if (stone < m) ++skip;
            else  skip = 0;
            if (skip == k) return false;
        }
        return true;
    }
}