import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2531 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> q = new LinkedList<Integer>();

        int N = Integer.parseInt(st.nextToken());   // 접시의 수
        int d = Integer.parseInt(st.nextToken());   // 초밥 가짓 수
        int k = Integer.parseInt(st.nextToken());   // 연속해서 먹는 수
        int c = Integer.parseInt(st.nextToken());   // 쿠폰 번호

        int[] arr = new int[N + k - 1];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < k - 1; i++) {
            arr[N + i] = arr[i];
        }

        int answer = 0;
        boolean[] visited = new boolean[d + 1];

        for (int i = 0; i < N; i++) {
            int cnt = 0;
            visited = new boolean[d + 1];

            for (int j = 0; j < k; j++) {
                if (!visited[arr[i + j]]) {
                    cnt++;
                    visited[arr[i + j]] = true;
                }
            }

            if (!visited[c]) {
                cnt++;
            }

            answer = Math.max(answer, cnt);
        }
        System.out.println(answer);
    }
}
