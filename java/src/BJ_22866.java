import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_22866 {

    static class Building {
        int no, height;

        public Building(int no, int height) {
            this.no = no;
            this.height = height;
        }
    }

    static class Answer {
        int cnt, near;

        public Answer() {}
        public Answer(int cnt, int near) {
            this.cnt = cnt;
            this.near = near;
        }

        @Override
        public String toString() {
            return "Answer{" +
                    "cnt=" + cnt +
                    ", near=" + near +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N + 1];
        Answer[] answers = new Answer[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Building> stack = new Stack<>();
        for (int i = 1; i <= N; i++) {
            answers[i] = new Answer();
            heights[i] = Integer.parseInt(st.nextToken());
        }
        
        // 오른쪽에서 왼쪽으로 이동
        for (int i = N; i > 0; i--) {
            int height = heights[i];
            if (stack.isEmpty()) {
                stack.push(new Building(i, height));
                continue;
            }
            while (!stack.isEmpty() && stack.peek().height <= height) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                answers[i].cnt += stack.size();
                answers[i].near = stack.peek().no;
            }
            stack.push(new Building(i, height));
        }

        stack = new Stack<>();
        // 왼쪽에서 오른쪽으로 이동
        for (int i = 0; i <= N; i++) {
            int height = heights[i];
            if (stack.isEmpty()) {
                stack.push(new Building(i, height));
                continue;
            }
            while (!stack.isEmpty() && stack.peek().height <= height) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                answers[i].cnt += stack.size();
                if (Math.abs(i - answers[i].near) >= Math.abs(i - stack.peek().no)) {
                    answers[i].near = stack.peek().no;
                }
            }
            stack.push(new Building(i, height));
        }

        for (int i = 1; i <= N; i++) {
            Answer now = answers[i];
            if (now.cnt == 0) {
                System.out.println(0);
                continue;
            }
            System.out.println(now.cnt + " " + now.near);
        }
    }
}
