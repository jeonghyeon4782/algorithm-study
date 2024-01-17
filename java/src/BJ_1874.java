import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BJ_1874 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int idx = 0;
        char[] answer = new char[n * 2];
        int answerIndex = 0;
        boolean chk = true;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num > idx) {
                for (int j = idx + 1; j < num + 1; j++) {
                    st.push(j);
                    answer[answerIndex++] = '+';
                }
                idx = num;
                st.pop();
                answer[answerIndex++] = '-';
            } else {
                int popNum = st.pop();
                if (popNum == num) {
                    answer[answerIndex++] = '-';
                } else {
                    chk = false;
                    break;
                }
            }
        }
        if (chk) {
            for (char c : answer) {
                System.out.println(c);
            }
        } else {
            System.out.println("NO");
        }
    }
}
