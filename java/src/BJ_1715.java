import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BJ_1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        long ans = 0;
        for (int i = 0; i < N; i++) {
            pq.offer(Long.parseLong(br.readLine()));
        }
        while (pq.size() > 1) {
            long first = pq.poll();
            long second = pq.poll();
            ans += first + second;
            pq.offer(first + second);
        }

        System.out.println(ans);
    }
}
