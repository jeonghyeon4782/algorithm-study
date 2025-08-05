import java.util.*;

class PG_118669 {

    class Node implements Comparable<Node> {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    boolean[] isGate;   // 출입구 여부 확인 배열
    boolean[] isSummit; // 봉우리 여부 확인 배열
    ArrayList<Node>[] graph; // 그래프
    int[] dist; // 정점까지의 Intensity 저장 배열

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];

        // 1. 변수 초기화
        isGate = new boolean[n + 1];
        isSummit = new boolean[n + 1];
        dist = new int[n + 1];
        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        // 그래프 생성
        for (int[] e : paths) {
            graph[e[0]].add(new Node(e[1], e[2]));
            graph[e[1]].add(new Node(e[0], e[2]));
        }

        // 출입구 확인 배열 초기화
        for (int gate : gates) {
            isGate[gate] = true;
        }

        // 봉우리 초기화
        for (int summit : summits) {
            isSummit[summit] = true;
        }

        // 다익스트라 실행
        dijkstra(gates);

        // intensity의 최소 구하기
        int minIntensity = Integer.MAX_VALUE;
        Arrays.sort(summits);

        for (int idx : summits) {
            if (minIntensity > dist[idx]) {
                answer[0] = idx;
                answer[1] = dist[idx];
                minIntensity = dist[idx];
            }
        }

        return answer;
    }

    public void dijkstra(int[] gates) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // pq에 출입구 추가
        for (int gate : gates) {
            pq.offer(new Node(gate, 0));
            dist[gate] = 0;
        }

        // 출입구 동시 출발
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (isSummit[now.v]) continue;

            // 이거 중요! 까먹지 말아라
            if (now.w > dist[now.v]) continue;

            // 만약 지금까지 온 경로 중 최댓값이 dist에 저장된 값 보다 작은 경우만 진입
            for (Node next : graph[now.v]) {
                if (dist[next.v] > Math.max(now.w, next.w) && !isGate[next.v]) {
                    dist[next.v] = Math.max(now.w, next.w);
                    pq.offer(new Node(next.v, dist[next.v]));
                }
            }
        }

    }
}