import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_1043 {

    static int N, M;
    static int[] trueArr, parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int ans = 0;
        int trueArrN = Integer.parseInt(st.nextToken());
        trueArr = new int[trueArrN];
        for (int i = 0; i < trueArrN; i++) {
            trueArr[i] = Integer.parseInt(st.nextToken());
        }
        parents = new int[N + 1];
        make();
        List<List<Integer>> party = new ArrayList<>();
        for (int i = 0; i <= M; i++) {
            party.add(new ArrayList<>());
        }
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            party.get(i).add(first);
            for (int j = 0; j < n - 1; j++) {
                int target = Integer.parseInt(st.nextToken());
                party.get(i).add(target);
                union(first, target);
            }
        }

        for (int i = 1; i <= M; i++) {
            boolean flag = false;
            for (int peo : party.get(i)) {
                for (int truePeo : trueArr) {
                    if (find(peo) == find(truePeo)) {
                        flag = true;
                    }
                }
            }
            if (!flag) ans++;
        }

        System.out.println(ans);
    }

    private static void make() {
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int n) {
        if (parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }

    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot) return;
        parents[aRoot] = bRoot;
    }
}
