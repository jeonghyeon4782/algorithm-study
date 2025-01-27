import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BJ_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> minusNum = new PriorityQueue<>();
        PriorityQueue<Integer> plusNum = new PriorityQueue<>(Collections.reverseOrder());
        int N = Integer.parseInt(br.readLine());
        long answer = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) plusNum.offer(num);
            else minusNum.offer(num);
        }

        answer += solve(minusNum);
        answer += solve(plusNum);
        System.out.println(answer);
    }

    private static long solve(PriorityQueue<Integer> num) {
        long answer = 0;

        while (num.size() > 1) {
            int first = num.poll();
            int second = num.poll();
            if (first == 1 || second == 1) answer += first + second;
            else answer += first * second;
        }

        while (!num.isEmpty()) {
            answer += num.poll();
        }

        return answer;
    }
}
