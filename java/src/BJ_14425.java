import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BJ_14425 {

    static class Node {
        Map<Character, Node> childs = new HashMap<>();
        boolean isEnd = false;
    }

    static class Tree {
        private final Node root = new Node();
        
        // 단어 삽입
        public void insert(String s) {
            Node now = root;
            for (char c : s.toCharArray()) {
                if (!now.childs.containsKey(c)) now.childs.put(c, new Node());
                now = now.childs.get(c);
            }
            now.isEnd = true;
        }

        // s와 정확히 일치하는 문자열이 있는 지 판단하는 메서드
        public boolean search(String s) {
            Node lastNode = getLastNode(s);
            if (lastNode == null || !lastNode.isEnd) return false;
            return true;
        }
        
        // 해당 문자열의 마지막 노드를 제거
        public void delete(String s) {
            Node lastNode = getLastNode(s);
            if (lastNode != null && lastNode.isEnd) {
                lastNode.isEnd = false;
            }
        }

        // 문자열의 마지막 노드 가져오기
        public Node getLastNode(String s) {
            Node now = root;
            for (char c : s.toCharArray()) {
                if (!now.childs.containsKey(c)) return null;
                now = now.childs.get(c);
            }
            return now;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int answer = 0;
        Tree tree = new Tree();
        for (int i = 0; i < N; i++) {
            tree.insert(br.readLine());
        }
        for (int i = 0; i < M; i++) {
            if (tree.search(br.readLine())) answer++;
        }
        System.out.println(answer);
    }
}
