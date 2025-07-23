import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_수만들기 {

    static class Node implements Comparable<Node> {
        int n;
        int cnt;

        public Node(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }

    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(br.readLine());
        for (int T = 1; T <= test; T++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            K = Integer.parseInt(br.readLine());
            int answer = dijkstra(K, 0);
            sb.append("#").append(T).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static int dijkstra(int n, int cnt) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(n, cnt));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.n == 0) return now.cnt;
            pq.offer(new Node(0, now.cnt + now.n));
            for (int a : arr) {
                pq.offer(new Node(now.n / a, now.cnt + now.n % a));
            }
        }
        return -1;
    }

}
