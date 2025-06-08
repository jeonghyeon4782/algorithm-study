import java.util.*;

class PG_12987 {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int b : B) {
            pq.offer(b);
        }
        for (int a : A) {
            while (!pq.isEmpty() && pq.peek() <= a) {
                pq.poll();
            }
            if (pq.isEmpty()) break;
            pq.poll();
            ++answer;
        }

        return answer;
    }
}