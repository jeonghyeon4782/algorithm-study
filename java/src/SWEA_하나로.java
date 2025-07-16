import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_하나로 {

    // 노드 클래스
    static class Node {
        int r, c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
                    '}';
        }
    }
    
    // 간선 클래스
    static class Edge implements Comparable<Edge> {
        int from, to;
        double weight;
        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
        @Override
        public String toString() {
            return "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }
    
    static int N;   // 노드의 수
    static Double E;   // 환경 부담금
    static int[] parents;   // 대표 노드 배열
    static Node[] nodeArr;  // 노드의 배열
    static Edge[] edgeArr;  // 간선의 배열(양방향이므로 N * (n-1) / 2 개)
    static int edgeIdx; // 간선을 추가하기 위한 인덱스
    static int[] input; // 조합을 위한 인덱스 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            // 1. setting
            N = Integer.parseInt(br.readLine());
            nodeArr = new Node[N];
            edgeArr = new Edge[N * (N - 1) / 2];
            input = new int[2];
            edgeIdx = 0;
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nodeArr[i] = new Node(Integer.parseInt(st1.nextToken()), Integer.parseInt(st2.nextToken()));
            }
            E = Double.parseDouble(br.readLine());

            // 2. 조합을 사용하여 edgeArr에 추가
            comb(0, 0);

            // 3. edgeArr 오름차순 정렬
            Arrays.sort(edgeArr);

            // 4. parents 초기화
            init();

            // 5. 크루스칼 알고리즘
            double answer = 0.0;
            int cnt = 0;
            for (Edge edge : edgeArr) {
                if (union(edge.from, edge.to)) {
                    answer += edge.weight;
                    cnt++;
                }
                if (cnt == N - 1) break;
            }

            // 6. 정답 출력
            sb.append(String.format("#%d %.0f\n", t, answer));
        }
        System.out.println(sb);
    }

    private static boolean union(int n1, int n2) {
        int root1 = find(n1);
        int root2 = find(n2);
        if (root1 == root2) return false;
        parents[root2] = root1;
        return true;
    }

    private static int find(int n) {
        if (parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }
    
    // parents 배열을 초기화 하는 메서드
    private static void init() {
        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    // 각 노드 중 2개를 뽑는 메서드
    private static void comb(int start, int cnt) {
        // 기저 조건
        if (cnt == 2) {
            Node from = nodeArr[input[0]];
            Node to = nodeArr[input[1]];
            double dist = Math.sqrt(Math.pow((double)from.r - to.r, 2) + Math.pow((double)from.c - to.c, 2));
            edgeArr[edgeIdx++] = new Edge(input[0], input[1], E * Math.pow(dist, 2));
            return;
        }
        
        // 유도 조건
        for (int i = start; i < N; i++) {
            input[cnt] = i;
            comb(i + 1, cnt + 1);
        }
    }
}
