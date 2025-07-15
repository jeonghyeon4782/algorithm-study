import java.util.HashMap;
import java.util.Map;

class SWEA_디렉토리 {

    class Node {
        String name;
        int cnt;
        Map<String, Node> childMap;
        Node parent;

        public Node(String name) {
            this.name = name;
            this.cnt = 0;
            this.childMap = new HashMap<>();
            this.parent = null;
        }

        // 경로의 가장 마지막 디렉토리를 반환하는 메서드
        public Node getNode(String path) {
            if (path.equals("/")) return this;
            String[] pathArr = path.split("/");
            Node now = this;    // 루트부터 시작
            for (String dir : pathArr) {
                if (dir.isEmpty()) continue;
                now = now.childMap.get(dir);
            }
            return now;
        }

        // 자신을 포함한 상위 디렉토리의 cnt를 업데이트하는 메서드
        public void updateCnt(int value) {
            Node now = this.parent;
            while (now != null) {
                now.cnt += value;
                now = now.parent;
            }
        }

        // 자신의 하위 디렉토리에 노드를 추가하는 메서드
        public void insertNode(Node node) {
            this.childMap.put(node.name, node);
            node.parent = this;
            this.cnt += node.cnt + 1;
            updateCnt(node.cnt + 1);
        }

        // 자신을 포함한 디렉토리를 삭제하는 메서드
        public void removeNode() {
            updateCnt(-(this.cnt + 1));
            this.parent.childMap.remove(this.name);
        }

        // 자신의 하위 디렉토리 갯수를 리턴하는 메서드
        public int getCnt() {
            return this.cnt;
        }

        // 자신의 하위 디렉토리로 다른 디렉토리를 복사하는 메서드
        public Node deepCopy(Node newParent) {
            Node copy = new Node(this.name);
            copy.parent = newParent;
            for (Node child : this.childMap.values()) {
                Node childCopy = child.deepCopy(copy);
                copy.childMap.put(childCopy.name, childCopy);
                copy.cnt += childCopy.cnt + 1;
            }
            return copy;
        }
    }

    Node root;

    void init(int n) {
        root = new Node("/");
    }

    void cmd_mkdir(char[] path, char[] name) {
        String p = new String(path).trim();
        String n = new String(name).trim();
        Node node = root.getNode(p);
        node.insertNode(new Node(n));
    }

    void cmd_rm(char[] path) {
        String p = new String(path).trim();
        Node node = root.getNode(p);
        node.removeNode();
    }

    void cmd_cp(char[] srcPath, char[] dstPath) {
        String p1 = new String(srcPath).trim();
        String p2 = new String(dstPath).trim();
        Node node1 = root.getNode(p1);
        Node node2 = root.getNode(p2);
        Node copy = node1.deepCopy(node2);
        node2.insertNode(copy);
    }

    void cmd_mv(char[] srcPath, char[] dstPath) {
        String p1 = new String(srcPath).trim();
        String p2 = new String(dstPath).trim();
        Node node1 = root.getNode(p1);
        Node node2 = root.getNode(p2);
        node1.removeNode();
        node2.insertNode(node1);
    }

    int cmd_find(char[] path) {
        String p = new String(path).trim();
        Node node = root.getNode(p);
        return node.getCnt();
    }
}
