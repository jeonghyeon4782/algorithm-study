import java.util.*;

class PG_172027 {
    static int answer = Integer.MAX_VALUE;
    static int[][] arr = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};

    public int solution(int[] picks, String[] minerals) {
        dfs(0, -1, picks, minerals);
        return answer;
    }

    public void dfs(int p, int now, int[] picks, String[] minerals) {
        // System.out.println(Arrays.toString(picks));
        // System.out.println("now : " + now + " p : " + p);

        if (picks[0] == 0 && picks[1] == 0 && picks[2] == 0) {
            answer = Math.min(answer, p);
            return;
        }

        if (now >= minerals.length) {
            answer = Math.min(answer, p);
            return;
        }

        for (int i = 0; i < 3; i++) {
            int newNow = now;
            int newP = p;
            if (picks[i] == 0) continue;
            for (int j = 0; j < 5; j++) {
                newNow++;
                if (newNow >= minerals.length) {
                    break;
                }
                if (minerals[newNow].equals("diamond")) {
                    newP += arr[i][0];
                } else if (minerals[newNow].equals("iron")) {
                    newP += arr[i][1];
                } else {
                    newP += arr[i][2];
                }
            }
            --picks[i];
            dfs(newP, newNow, picks, minerals);
            ++picks[i];
        }

    }
}