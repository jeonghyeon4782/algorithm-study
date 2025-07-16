import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_창용마을무리의개수 {
    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Set<Integer> set;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int n1, n2;
            init();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                n1 = Integer.parseInt(st.nextToken());
                n2 = Integer.parseInt(st.nextToken());
                union(n1, n2);
            }
            set = new HashSet<>();
            for (int i = 1; i <= N; i++) {
                set.add(find(i));
            }
            sb.append("#").append(t).append(" ").append(set.size()).append("\n");
        }
        System.out.print(sb);
    }

    private static void init() {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int n) {
        if (parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }

    private static boolean union(int n1, int n2) {
        int root1 = find(n1);
        int root2 = find(n2);
        if (root1 == root2) return false;   // 벌써 같은 집합
        parents[root2] = root1;    // 대장은 root1
        return true;
    }
}
