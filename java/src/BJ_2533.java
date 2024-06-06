import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_2533 {
    static int N;
    static int[][] dp;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        graph = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2]; // 0 : 얼리어탑터가 아닐 경우, 1 : 얼리어답터일 경우
        visited = new boolean[N + 1];
        // 그래프 생성
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }
        // 트리 생성
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
        dfs(1, 1);
        System.out.println(Math.min(dp[1][1], dp[1][0]));
    }

    public static void dfs(int num, int depth) {
        dp[num][0] = 0; // 얼리어탑터가 아닐 경우는 0으로 초기화
        dp[num][1] = 1; // 얼리어탑터일 경우는 무조건 자신은 포함이므로 1로 초기화
        visited[num] = true;

        for (int next : graph.get(num)) {
            if (!visited[next]) {
                dfs(next, depth + 1);
                dp[num][0] += dp[next][1];
                dp[num][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
}