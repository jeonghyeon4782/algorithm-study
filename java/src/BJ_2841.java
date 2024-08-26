import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_2841 {
    static int N, P, result;
    static Stack<Integer>[] stackArr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        stackArr = new Stack[7];

        for (int i = 1; i < 7; i++) {
            stackArr[i] = new Stack<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int rope = Integer.parseInt(st.nextToken());
            int fret = Integer.parseInt(st.nextToken());

            if (stackArr[rope].isEmpty()) {
                stackArr[rope].push(fret);
                result++;
                continue;
            }

            while (!stackArr[rope].isEmpty() && stackArr[rope].peek() > fret) {
                stackArr[rope].pop();
                result++;
            }

            if (!stackArr[rope].isEmpty() && stackArr[rope].peek() == fret) {
                continue;
            }

            stackArr[rope].push(fret);
            result++;

        }
        System.out.println(result);
    }
}
