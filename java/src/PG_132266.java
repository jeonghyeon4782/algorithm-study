import java.util.*;

class PG_132266 {

    class Node implements Comparable<Node> {
        int v, weight;
        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    ArrayList<ArrayList<Node>> graph;
    int[] answer;
    int N;  // 정점의 갯수

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        N = n;
        answer = new int[sources.length];
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(new Node(road[1], 1));
            graph.get(road[1]).add(new Node(road[0], 1));
        }

        dijkstra(destination, sources);

        return answer;
    }

    public void dijkstra(int s, int[] sources) {
        boolean[] visited = new boolean[N + 1];
        visited[s] = true;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (!visited[now.v]) visited[now.v] = true;
            for (Node next : graph.get(now.v)) {
                if (!visited[next.v] && now.weight + next.weight < dist[next.v]) {
                    dist[next.v] = now.weight + next.weight;
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
            if (answer[i] == Integer.MAX_VALUE) answer[i] = -1;
        }
    }
}