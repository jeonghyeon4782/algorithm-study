import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ_11003 {

    static class Node {
        int idx, value;
        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Deque<Node> deque = new LinkedList<>();
        deque.offerFirst(new Node(0, nums[0]));
        System.out.print(deque.peekFirst().value + " ");

        for (int i = 1; i < N; i++) {
            while (!deque.isEmpty() && deque.peekLast().value > nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(new Node(i, nums[i]));
            while (!deque.isEmpty() && deque.peekFirst().idx <= i - L) {
                deque.pollFirst();
            }
            sb.append(deque.peekFirst().value + " ");
        }
        br.close();
        System.out.println(sb.toString());
    }
}
