import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BJ_1874 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Character> answer = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        int idx = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > idx) {
                for (int j = idx + 1; j <= num; j++) {
                    stack.push(j);
                    answer.add('+');
                }
                stack.pop();
                answer.add('-');
                idx = num;
            } else {
                if (stack.isEmpty() || stack.peek() != num) {
                    System.out.println("NO");
                    return;
                } else {
                    stack.pop();
                    answer.add('-');
                }
            }
        }
        for (char c : answer) {
            System.out.println(c);
        }
    }
}
