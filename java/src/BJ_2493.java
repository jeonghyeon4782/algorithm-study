import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_2493 {
    static class Top {
        int idx, height;

        public Top(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<Top> tops = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {

            int height = Integer.parseInt(st.nextToken());

            while (!tops.isEmpty() && tops.peek().height < height) {
                tops.pop();
            }

            if (tops.isEmpty()) {
                System.out.print(0 + " ");
            } else {
                System.out.print(tops.peek().idx + " ");
            }

            tops.push(new Top(i, height));
        }
    }
}
