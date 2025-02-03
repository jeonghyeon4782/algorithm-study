import java.util.*;

class PG_72413 {

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

    static int[] sDist, dist;
    static boolean[] visited;
    static List<List<Node>> graph;
    static int N;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        graph = new ArrayList<>();
        int answer = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] fare : fares) {
            graph.get(fare[0]).add(new Node(fare[1], fare[2]));
            graph.get(fare[1]).add(new Node(fare[0], fare[2]));
        }

        solve(s, -1);

        for (int i = 1; i <= N; i++) {
            solve(i, a);
            int aDist = dist[a];
            solve(i, b);
            int bDist = dist[b];
            if (answer > sDist[i] + aDist + bDist) {
                answer = sDist[i] + aDist + bDist;
            }
        }

        return answer;
    }

    public void solve(int s, int e) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited = new boolean[N + 1];
        if (e == -1) {
            sDist = new int[N + 1];
            Arrays.fill(sDist, Integer.MAX_VALUE);
            sDist[s] = 0;
        } else {
            dist = new int[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[s] = 0;
        }
        pq.offer(new Node(s, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (!visited[now.v]) visited[now.v] = true;
            if (now.v == e) return;

            for (Node next : graph.get(now.v)) {
                if (e != -1 && !visited[next.v] && now.weight + next.weight < dist[next.v]) {
                    dist[next.v] = now.weight + next.weight;
                    pq.offer(new Node(next.v, dist[next.v]));
                } else if (e == -1 && !visited[next.v] && now.weight + next.weight < sDist[next.v]) {
                    sDist[next.v] = now.weight + next.weight;
                    pq.offer(new Node(next.v, sDist[next.v]));
                }
            }
        }
    }
}