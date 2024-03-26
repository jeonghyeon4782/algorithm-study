import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1774 {
    static List<Point> nodeList;
    static List<Edge> edgeList;
    static int V, P;    // 노드 갯수, 이미 열견된 통로 갯수
    static int[] parent;
    static int[] input; // 조합 사용

    static class Edge implements Comparable<Edge>{
        int from, to;
        double weight;
        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        @Override
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

    public static void main(String[] args) throws IOException {
        // 기본 셋팅
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        nodeList = new ArrayList<>();
        edgeList = new ArrayList<>();
        input = new int[2];

        // 노드 입력 받기
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            nodeList.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        // 조합 사용
        comb(0, 0);

        Collections.sort(edgeList);
        make();
        // 미리 연결된 통로는 union 처리
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        double weight = 0;
        for (Edge edge : edgeList) {
            if (!union(edge.from, edge.to)) continue;   // 만약 같은 집합이라면 통과
            weight += edge.weight;
        }

        System.out.printf("%.2f", weight);
    }

    // 노드로 2개의 조합 생성 후 각각의 거리를 계산하여 간선리스트에 추가
    public static void comb(int cnt, int start) {
        if (cnt == 2) {
            Point p1 = nodeList.get(input[0]);
            Point p2 = nodeList.get(input[1]);
            double weight = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
            edgeList.add(new Edge(input[0] + 1, input[1] + 1, weight));
            return;
        }

        for (int i = start; i < nodeList.size(); i++) {
            input[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }

    // 자신을 대표자로 만들기
    public static void make() {
        parent = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            parent[i] = i;
        }
    }

    // a가 속한 트리의 루트 찾기
    public static int find(int a) {
        if (a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;   // 같은 집합에 속함
        parent[bRoot] = aRoot;              // 합치기
        return true;
    }
}
