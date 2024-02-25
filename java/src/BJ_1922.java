import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1922 {
    static class Edge implements Comparable<Edge>{
        int from, to, weight;
        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int N, M;
    static List<Edge> edges;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int result = 0;
        int cnt = 0;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edges = new ArrayList<>();
        StringTokenizer st = null;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, weight));
        }

        make();
        Collections.sort(edges);

        for (Edge edge : edges) {
            if (!union(edge.from, edge.to)) {
                continue;
            }
            result += edge.weight;
            if (++cnt == N - 1) break;
        }
        System.out.println(result);
    }

    private static void make() {
        parents = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
}
