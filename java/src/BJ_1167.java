import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1167 {

    static class Edge {
        int next, weight;

        public Edge(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }

    static int N;
    static int[] weightArr;
    static boolean[] visited;
    static List<List<Edge>> tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        weightArr = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            Queue<Integer> q = new LinkedList<>();
            while (st.hasMoreTokens()) {
                q.offer(Integer.parseInt(st.nextToken()));
            }
            while (q.size() >= 2) {
                int first = q.poll();
                int second = q.poll();
                tree.get(idx).add(new Edge(first, second));
            }
        }
        bfs(1);
        int maxIdx = 0;
        int maxWeight = 0;
        for (int i = 1; i <= N; i++) {
            if (maxWeight < weightArr[i]) {
                maxIdx = i;
                maxWeight = weightArr[i];
            }
        }
        weightArr = new int[N + 1];
        bfs(maxIdx);

        maxWeight = 0;
        for (int i = 1; i <= N; i++) {
            maxWeight = Math.max(maxWeight, weightArr[i]);
        }

        System.out.println(maxWeight);
    }

    private static void bfs(int s) {
        visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(s);
        visited[s] = true;
        while (!q.isEmpty()) {
            int now = q.poll();
            for (Edge edge : tree.get(now)) {
                if (!visited[edge.next]) {
                    q.offer(edge.next);
                    visited[edge.next] = true;
                    weightArr[edge.next] = weightArr[now] + edge.weight;
                }
            }
        }
    }
}
