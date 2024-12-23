import java.util.*;
import java.io.*;

class PG_258711 {

    static int[] answer;
    static boolean[] visited;
    static int outCnt, nodeCnt;
    static boolean isEight; // 8자형 그래프인지 여부 확인
    static ArrayList<ArrayList<Integer>> graph;
    static int[] in;
    static int[] out;

    public int[] solution(int[][] edges) {
        // Setting
        int removeNode = -1;    // 삭제할 노드 번호
        int n = 0;  // 총 노드의 갯수
        for (int[] edge : edges) {
            n = Math.max(n, edge[0]);
            n = Math.max(n, edge[1]);
        }
        in = new int[n + 1];  // 들어오는 노드의 갯수
        out = new int[n + 1]; // 나가는 노드의 갯수
        answer = new int[4];
        visited = new boolean[n + 1];
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            in[edge[1]]++;
            out[edge[0]]++;
        }
        // 삭제할 노드 찾기
        for (int i = 1; i <= n; i++) {
            if (out[i] >= 2 && in[i] == 0) removeNode = i;
        }
        answer[0] = removeNode;

        // 각각의 그래프 갯수 구하기
        for (int node : graph.get(removeNode)) {
            visited[node] = true;
            dfs(node, node);
        }

        return answer;
    }

    public void dfs(int first, int node) {
        // 막대형 그래프
        if (out[node] == 0) {
            answer[2]++;
            return;
        }
        // 8자형 그래프
        if (out[node] >= 2) {
            answer[3]++;
            return;
        }
        for (int next : graph.get(node)) {
            // 도넛형 그래프
            if (next == first) {
                answer[1]++;
                return;
            }
            if (!visited[next]) {
                visited[next] = true;
                dfs(first, next);
            }
        }
    }
}