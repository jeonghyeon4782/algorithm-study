import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_힙 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
            sb.append("#").append(t).append(" ");
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int order = Integer.parseInt(st.nextToken());
                if (order == 1) {
                    pq.offer(Integer.parseInt(st.nextToken()));
                } else {
                    if (!pq.isEmpty()) sb.append(pq.poll()).append(" ");
                    else sb.append("-1").append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
