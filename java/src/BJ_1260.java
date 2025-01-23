import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1260 {

    static int V, E, S;
    static List<ArrayList<Integer>> edgeList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            edgeList.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            edgeList.get(s).add(e);
            edgeList.get(e).add(s);
        }
        visited = new boolean[V + 1];
        dfs(S);
        System.out.println();
        visited = new boolean[V + 1];
        bfs(S);
    }

    private static void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        visited[s] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            Collections.sort(edgeList.get(now));
            System.out.print(now + " ");
            for (int next : edgeList.get(now)) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
    }

    private static void dfs(int now) {
        visited[now] = true;
        System.out.print(now + " ");
        Collections.sort(edgeList.get(now));
        for (int next : edgeList.get(now)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}
