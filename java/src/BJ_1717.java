import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1717 {
    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        makeSet();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            if (order == 0) unionSet(x, y);
            else {
                if (findSet(x) == findSet(y)) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }
        System.out.println(sb.toString().trim());
    }

    // 각자 집합 만들기
    private static void makeSet() {
        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int findSet(int x) {
        if (parents[x] == x) return x;
        return parents[x] = findSet(parents[x]);
    }

    private static void unionSet(int x, int y) {
        int aRoot = findSet(x);
        int bRoot = findSet(y);

        if (aRoot == bRoot) return;

        parents[bRoot] = aRoot;
    }
}
