import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_13023 {

    static int V, E;
    static boolean[] visited;
    static boolean flag;
    static List<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(s).add(e);
            graph.get(e).add(s);
        }
        for (int i = 0; i < V; i++) {
            visited[i] = true;
            dfs(i, 0);
            if (flag) {
                System.out.println(1);
                return;
            }
            visited[i] = false;
        }
        System.out.println(0);
    }

    private static void dfs(int now, int depth) {

        if (depth == 4 || flag) {
            flag = true;
            return;
        }

        for (int next : graph.get(now)) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, depth + 1);
                visited[next] = false;
            }
        }
    }
}
