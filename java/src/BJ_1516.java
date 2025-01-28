import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_1516 {

    static int[] in; // 각 노드의 진입차수
    static int N; // 건물의 개수
    static int[] answer, time; // 각 건물의 최소 건설 시간, 건설 소요 시간
    static List<List<Integer>> graph; // 그래프를 저장할 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        answer = new int[N + 1];
        time = new int[N + 1];
        in = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 입력 처리
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1) break;
                graph.get(num).add(i); // num → i 방향으로 간선 추가
                in[i]++; // i의 진입차수 증가
            }
        }

        Queue<Integer> q = new LinkedList<>();

        // 진입차수가 0인 노드를 큐에 추가
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) {
                q.offer(i);
                answer[i] = time[i]; // 초기값: 자기 자신을 짓는 데 걸리는 시간
            }
        }

        // 위상 정렬 수행
        while (!q.isEmpty()) {
            int now = q.poll();

            for (int next : graph.get(now)) {
                in[next]--;
                answer[next] = Math.max(answer[next], answer[now] + time[next]); // 최대 시간 갱신

                if (in[next] == 0) {
                    q.offer(next);
                }
            }
        }

        // 결과 출력
        for (int i = 1; i <= N; i++) {
            System.out.println(answer[i]);
        }
    }
}
