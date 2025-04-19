import java.util.*;

class PG_150365 {

    int[] dr = {1, 0, 0, -1};
    int[] dc = {0, -1, 1, 0};
    int R, C, sR, sC, eR, eC, K;
    boolean flag = false;
    String answer = null;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        R = n;
        C = m;
        sR = x - 1;
        sC = y - 1;
        eR = r - 1;
        eC = c - 1;
        K = k;

        int dist = Math.abs(sR - eR) + Math.abs(sC - eC);
        if (dist > K || (K - dist) % 2 != 0) {
            return "impossible";
        }

        dfs(sR, sC, 0, new StringBuilder());

        if (answer == null) answer = "impossible";
        return answer;
    }

    public void dfs(int r, int c, int depth, StringBuilder sb) {
        // 기저 조건
        if (r == eR && c == eC && depth == K) {
            flag = true;
            answer = sb.toString();
            return;
        }
        if (flag) return;
        int dist = Math.abs(r - eR) + Math.abs(c - eC);
        if (dist > K - depth) return;

        // 유도 조건
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (0 <= nr && nr < R && 0 <= nc && nc < C) {
                if (d == 0) {
                    sb.append("d");
                } else if (d == 1) {
                    sb.append("l");
                } else if (d == 2) {
                    sb.append("r");
                } else {
                    sb.append("u");
                }
                dfs(nr, nc, depth + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}