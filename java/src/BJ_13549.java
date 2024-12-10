import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_13549 {

    static class Info {
        int num, dist;

        public Info(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }

    static int N, K;
    static int[] visited = new int[200001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 1; i < 200001; i++) {
            visited[i] = Integer.MAX_VALUE;
        }

        if (N >= K) {
            System.out.println(N - K);
            return;
        }
        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Info> q = new LinkedList<>();
        q.offer(new Info(N, 0));
        visited[N] = 0;
        int ans = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Info cur = q.poll();
            if (cur.num == K) {
                ans = Math.min(ans, cur.dist);
            }

            int nNum = 0;
            for (int i = 0; i < 3; i++) {
                if (i == 0){
                    nNum = cur.num * 2;
                    if (0 < nNum && nNum < 200001 && visited[nNum] > cur.dist) {
                        q.offer(new Info(nNum, cur.dist));
                        visited[nNum] = cur.dist;
                    }
                }
                else if (i == 1) {
                    nNum = cur.num + 1;
                    if (0 < nNum && nNum < 200001 && visited[nNum] > cur.dist + 1) {
                        q.offer(new Info(nNum, cur.dist + 1));
                        visited[nNum] = cur.dist + 1;
                    }
                } else if (i == 2) {
                    nNum = cur.num - 1;
                    if (0 < nNum && nNum < 200001 && visited[nNum] > cur.dist + 1) {
                        q.offer(new Info(nNum, cur.dist + 1));
                        visited[nNum] = cur.dist + 1;
                    }
                }
            }
        }
        return ans;
    }
}
