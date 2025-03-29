import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String bomb = br.readLine();
        Stack<Character> stack = new Stack<>();
        char[] bombArr = new char[bomb.length()];
        for (int i = 0; i < bomb.length(); i++) {
            bombArr[i] = bomb.charAt(i);
        }
        for (int i = 0; i < s.length(); i++) {
            stack.push(s.charAt(i));

            if (stack.size() >= bombArr.length) {
                if (stack.peek() == bombArr[bombArr.length - 1]) {
                    int cnt = 0;
                    for (int j = 0; j < bombArr.length; j++) {
                        if (stack.get(stack.size() - 1 - j) == bombArr[bombArr.length - 1 - j]) ++cnt;
                    }
                    if (cnt == bombArr.length) {
                        for (int j = 0; j < bombArr.length; j++) {
                            stack.pop();
                        }
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}
