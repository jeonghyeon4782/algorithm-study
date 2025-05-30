import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BJ_1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (maxPQ.size() != minPQ.size()) {
                minPQ.offer(num);
            } else {
                maxPQ.offer(num);
            }
            if (!maxPQ.isEmpty() && !minPQ.isEmpty() && maxPQ.peek() > minPQ.peek()) {
                int a = maxPQ.poll();
                int b = minPQ.poll();
                minPQ.offer(a);
                maxPQ.offer(b);
            }
            sb.append(maxPQ.peek()).append("\n");
        }
        System.out.println(sb.toString());
    }
}
