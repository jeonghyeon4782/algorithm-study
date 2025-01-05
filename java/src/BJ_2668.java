import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2668 {

    static int[][] map;
    static boolean[] visited;
    static Set<Integer> answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[2][N + 1];
        answer = new HashSet<>();
        visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            map[0][i] = i;
            map[1][i] = Integer.parseInt(br.readLine());
        }
        for (int i = 1; i < N + 1; i++) {
            if (map[0][i] == map[1][i]) {
                answer.add(i);
                visited[i] = true;
            }
        }
        for (int i = 1; i < N + 1; i++) {
            Set<Integer> set = new HashSet<>();
            set.add(i);
            visited[i] = true;
            dfs(i, i, set);
            visited[i] = false;
        }
        System.out.println(answer.size());
        List<Integer> list = new ArrayList<>(answer);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    private static void dfs(int now, int target, Set<Integer> set) {
        int next = map[1][now];

        if (next == target) {
            set.add(next);
            answer.addAll(set);
            return;
        }

        if (!visited[next]) {
            visited[next] = true;
            set.add(next);
            dfs(next, target, set);
            visited[next] = false;
        }
    }

}
