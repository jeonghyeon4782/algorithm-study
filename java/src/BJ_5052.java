import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_5052 {

    static class Node {
        Node[] childs = new Node[10];
        boolean isEnd = false;
    }

    static class Tree {
        private final Node root = new Node();

        public boolean isAdd(String s) {
            Node now = root;

            for (int i = 0; i < s.length(); i++) {
                int num = s.charAt(i) - '0';

                if (now.childs[num] == null) {
                    now.childs[num] = new Node();
                }

                now = now.childs[num];

                if (now.isEnd) return false;    // 불가능
            }

            now.isEnd = true;

            for (int i = 0; i <= 9; i++) {
                if (now.childs[i] != null) return false;
            }

            return true;
        }

        public void remove(String s) {
            Node now = search(s);

            if (now == null) return;

            now.isEnd = false;
        }

        public Node search(String s) {
            Node now = root;

            for (int i = 0; i < s.length(); i++) {
                int num = s.charAt(i) - '0';

                if (now.childs[num] == null) {
                    return null;
                }

                now = now.childs[num];
            }

            if (!now.isEnd) return null;

            return now;
        }
    }

    static Tree tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int test = Integer.parseInt(br.readLine());

        for (int T = 1; T <= test; T++) {
            tree = new Tree();
            int n = Integer.parseInt(br.readLine());
            boolean flag = true;
            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                if (!tree.isAdd(s)) {
                    flag = false;
                }
            }
            System.out.println(!flag ? "NO" : "YES");
        }
    }
}
