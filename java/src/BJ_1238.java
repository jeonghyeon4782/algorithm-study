import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1238 {

	static class Node implements Comparable<Node> {
		int v, weight;

		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}

		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	static int V, E, X ;
	static int[] dist, revDist;
	static boolean[] visited;
	static List<List<Node>> graph, revGraph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ans = Integer.MIN_VALUE;
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		dist = new int[V + 1];
		revDist = new int[V + 1];
		graph = new ArrayList<>();
		revGraph = new ArrayList<>();
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
			revGraph.add(new ArrayList<>());
			dist[i] = Integer.MAX_VALUE;
			revDist[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, weight));
			revGraph.get(to).add(new Node(from, weight));
		}

		solve(X, -1, graph, dist);
		solve(X, -1, revGraph, revDist);

		for (int i = 1; i <= V; i++) {
			ans = Math.max(ans, dist[i] + revDist[i]);
		}

		System.out.println(ans);
	}

	public static void solve(int s, int e, List<List<Node>> graph, int[] dist) {
		visited = new boolean[V + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(s, 0));
		dist[s] = 0;

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (now.v == e) {
				return;
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
