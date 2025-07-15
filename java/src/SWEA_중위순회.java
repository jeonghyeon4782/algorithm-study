import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_중위순회 {

    static StringBuilder sb;

    static class Node {
        char ch;
        int left, right;
        public Node(char ch) {
            this.ch = ch;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Node[] nodeArr;
        for (int T = 1; T <= 10; T++) {
            int N = Integer.parseInt(br.readLine());
            sb = new StringBuilder();
            nodeArr = new Node[N + 1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                nodeArr[i] = new Node(st.nextToken().charAt(0));
                switch (st.countTokens()) {
                    case 1:
                        nodeArr[i].left = Integer.parseInt(st.nextToken());
                        break;
                    case 2:
                        nodeArr[i].left = Integer.parseInt(st.nextToken());
                        nodeArr[i].right = Integer.parseInt(st.nextToken());
                        break;
                }
            }
            inOrder(nodeArr, 1);
            System.out.printf("#%d %s\n", T, sb);
        }
    }

    public static void inOrder(Node[] nodeArr, int now) {
        if (now == 0) return;
        inOrder(nodeArr, nodeArr[now].left);
        sb.append(nodeArr[now].ch);
        inOrder(nodeArr, nodeArr[now].right);
    }
}
