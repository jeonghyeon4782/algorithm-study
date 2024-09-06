import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1389 {

    static int N, M;
    static ArrayList<ArrayList<Integer>> relationshipList;
    static boolean[] visited;
    static int result, minBaconCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        relationshipList = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        minBaconCnt = Integer.MAX_VALUE;

        for (int i = 0; i <= N; i++) {
            relationshipList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relationshipList.get(a).add(b);
            relationshipList.get(b).add(a);
        }

        for (int i = 1; i <= N; i++) {
            int baconCnt = bfs(i);
            if (baconCnt < minBaconCnt) {
                minBaconCnt = baconCnt;
                result = i;
            }
        }
        System.out.println(result);
    }

    private static int bfs(int i) {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N + 1];
        q.offer(new int[]{i, 0});
        visited[i] = true;
        int baconCnt = 0;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            baconCnt += now[1];

            for (int people : relationshipList.get(now[0])) {
                if (visited[people]) continue;
                q.offer(new int[] {people, now[1] + 1});
                visited[people] = true;
            }
        }

        return baconCnt;
    }
}
