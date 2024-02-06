import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_13913 {
    static int n, m;
    static Queue<String[]> q;
    static boolean[] chk = new boolean[200001];

    static boolean is_valid(int x) {
        return 0 <= x && x < 200001;
    }

    static String bfs(int x) {
        q = new LinkedList<>();
        chk[x] = true;
        q.offer(new String[]{String.valueOf(x), String.valueOf(x)});

        while (!q.isEmpty()) {
            String[] now = q.poll();
            int nowNum = Integer.parseInt(now[0]);
            String nowRoute = now[1];

            if (nowNum == m) {
                return nowRoute;
            }

            if (is_valid(nowNum + 1) && !chk[nowNum + 1]) {
                q.offer(new String[]{String.valueOf(nowNum + 1), nowRoute + ":" + (nowNum + 1)});
                chk[nowNum + 1] = true;
            }

            if (is_valid(nowNum - 1) && !chk[nowNum - 1]) {
                q.offer(new String[]{String.valueOf(nowNum - 1), nowRoute + ":" + (nowNum - 1)});
                chk[nowNum - 1] = true;
            }

            if (is_valid(nowNum * 2) && !chk[nowNum * 2]) {
                q.offer(new String[]{String.valueOf(nowNum * 2), nowRoute + ":" + (nowNum * 2)});
                chk[nowNum * 2] = true;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        String answer = bfs(n);
        String[] ans = answer.split(":");
        System.out.println(ans.length - 1);
        for (String num : ans) {
            System.out.print(num + " ");
        }
    }
}
