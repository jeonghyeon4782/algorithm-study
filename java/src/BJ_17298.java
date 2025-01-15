import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_17298 {
    static class Node {
        int idx, num;
        public Node(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] answer = new int[N];
        Stack<Node> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            while (!stack.isEmpty() && stack.peek().num < num) {
                Node node = stack.pop();
                answer[node.idx] = num;
            }
            stack.push(new Node(i, num));
        }
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            answer[node.idx] = -1;
        }

        for (int num : answer) {
            sb.append(num + " ");
        }
        System.out.println(sb.toString());
    }
}
