import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1504 {
    static class Node implements Comparable<Node>{
        int v, weight;
        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int[] dist;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> graph;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());    // 정점 갯수
        M = Integer.parseInt(br.readLine());    // 간선 갯수
        graph = new ArrayList<>();
        dist = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, weight));
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        dijkstra(s, e);
    }

    private static void dijkstra(int s, int e) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[s] = 0;
        pq.offer(new Node(s, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.v == e) {
                System.out.println(dist[e]);
                break;
            }
            if (!visited[now.v]) visited[now.v] = true;

            for (Node next : graph.get(now.v)) {
                if (!visited[next.v] && now.weight + next.weight < dist[next.v]) {
                    dist[next.v] = now.weight + next.weight;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
    }
}
