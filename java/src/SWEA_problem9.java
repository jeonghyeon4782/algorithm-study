import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;

class SWEA_problem9 {

    class Node implements Comparable<Node> {
        int v; // 노드 번호
        int w; // 비용
        int cnt; // 할인권 사용 횟수

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public Node(int v, int w, int cnt) {
            this.v = v;
            this.w = w;
            this.cnt = cnt;
        }

        public int compareTo(Node o) {
            return this.w - o.w;
        }
    }

    Map<Integer, int[]> roadMap = new HashMap<>(); // key : 도로 ID, value : 도로의 출발지 도착지
    ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    PriorityQueue<Node> pq = new PriorityQueue<>(); // 노드의 우선순위 큐
    int[][] dist; // 행 : 노드 번호, 열 : 할인권 사용 횟수
    int citySize;

    public void init(int N, int K, int mId[], int sCity[], int eCity[], int mToll[]) {
        roadMap.clear();
        graph.clear();
        dist = new int[N][11];
        citySize = N;

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            add(mId[i], sCity[i], eCity[i], mToll[i]);
        }

        return;
    }

    public void add(int mId, int sCity, int eCity, int mToll) {
        roadMap.computeIfAbsent(mId, k -> new int[2]);
        roadMap.get(mId)[0] = sCity;
        roadMap.get(mId)[1] = eCity;

        graph.get(sCity).add(new Node(eCity, mToll));

        return;
    }

    public void remove(int mId) {
        int[] remove = roadMap.get(mId);
        ListIterator<Node> iterator = graph.get(remove[0]).listIterator();

        while (iterator.hasNext()) {
            Node now = iterator.next();

            if (now.v == remove[1]) {
                iterator.remove();
                break;
            }
        }

        roadMap.remove(mId);

        return;
    }

    public int cost(int M, int sCity, int eCity) {
        pq.clear();
        for (int i = 0; i < citySize; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[sCity][0] = 0;
        pq.offer(new Node(sCity, 0, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.w > dist[now.v][now.cnt]) {
                continue;
            }

            if (now.v == eCity) return dist[now.v][now.cnt];

            for (Node next : graph.get(now.v)) {
                // 할인권 사용 X
                if (dist[next.v][now.cnt] > now.w + next.w) {
                    dist[next.v][now.cnt] = now.w + next.w;
                    pq.offer(new Node(next.v, dist[next.v][now.cnt], now.cnt));
                }

                // 할인권 사용 O
                if (now.cnt + 1 <= M && dist[next.v][now.cnt + 1] > now.w + (next.w / 2)) {
                    dist[next.v][now.cnt + 1] = now.w + (next.w / 2);
                    pq.offer(new Node(next.v, dist[next.v][now.cnt + 1], now.cnt + 1));
                }
            }
        }

        return -1;
    }

}