import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_15591 {

    static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, weight));
            graph.get(to).add(new Node(from, weight));
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            visited = new boolean[N + 1];
            q.clear();
            q.offer(new Node(target, Integer.MAX_VALUE));
            visited[target] = true;
            int cnt = 0;

            while (!q.isEmpty()) {
                Node now = q.poll();

                for (Node next : graph.get(now.v)) {
                    if (!visited[next.v]) {
                        int min = Math.min(now.w, next.w);
                        if (min >= K) {
                            cnt++;
                            visited[next.v] = true;
                            q.offer(new Node(next.v, min));
                        }
                    }
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}
