import java.util.*;

class PG_43162 {

    List<List<Integer>> map = new ArrayList<>();
    boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
        }
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r == c) continue;
                if (computers[r][c] == 1) map.get(r).add(c);
            }
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ++answer;
                visited[i] = true;
                dfs(i);
            }
        }

        return answer;
    }

    public void dfs(int now) {
        for (int next : map.get(now)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next);
            }
        }
    }
}