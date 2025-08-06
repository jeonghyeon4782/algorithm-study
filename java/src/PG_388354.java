import java.util.*;

class PG_388354 {

    boolean[] visited;  // 방문 배열
    ArrayList<Integer>[] graph; // 그래프
    Map<Integer, Integer> mapper;   // 노드 번호 >> 인덱스 변환 매퍼
    int N;  // 노드의 갯수
    int[] edgeCnt;  // 노드에 연결된 간선의 수
    int[] rootCnt;  // 홀, 짝, 역홀, 역짝
    int[] notRootCnt;  // 홀, 짝, 역홀, 역짝
    int treeCnt;    // 트리에 속한 노드의 수

    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];

        // 변수 초기화
        N = nodes.length;
        graph = new ArrayList[N];
        mapper = new HashMap<>();
        visited = new boolean[N];
        edgeCnt = new int[N];
        int idx = 0;

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();

            // 좌표 압축
            mapper.put(nodes[i], i);
        }

        // 그래프 초기화
        for (int[] edge : edges) {
            int from = mapper.get(edge[0]);
            int to = mapper.get(edge[1]);

            // edgeCnt 추가
            edgeCnt[from]++;
            edgeCnt[to]++;

            // 그래프에 간선 추가
            graph[from].add(to);
            graph[to].add(from);
        }

        // 각 독립 그래프 별로 카운트
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                rootCnt = new int[4];
                notRootCnt = new int[4];
                treeCnt = 0;

                // System.out.println("현재 " + nodes[i] + "번 bfs 들어갑니다.");

                bfs(i, nodes);
                // System.out.println("treeCnt : " + treeCnt);
                // System.out.println(Arrays.toString(rootCnt));
                // System.out.println(Arrays.toString(notRootCnt));

                // 만약 노드 갯수가 한 개라면?
                if (treeCnt == 1) {
                    if (nodes[i] % 2 == 1) {
                        answer[1]++;
                    } else {
                        answer[0]++;
                    }
                    continue;
                }

                // 홀짝트리 판단
                for (int j = 0; j <= 1; j++) {
                    if (rootCnt[j] == 1 && rootCnt[j] + notRootCnt[0] + notRootCnt[1] == treeCnt) {
                        answer[0]++;
                        break;
                    }
                }

                // 역홀짝트리 판단
                for (int j = 2; j <= 3; j++) {
                    if (rootCnt[j] == 1 && rootCnt[j] + notRootCnt[2] + notRootCnt[3] == treeCnt) {
                        answer[1]++;
                        break;
                    }
                }

                System.out.println(Arrays.toString(answer));
            }
        }

        return answer;
    }

    public void bfs(int start, int[] nodes) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int now = q.poll();
            treeCnt++;

            if (!visited[now]) visited[now] = true;

            int num = nodes[now];
            int edge = edgeCnt[now];

            rootCnt[checkNode(num, edge, nodes)]++;
            notRootCnt[checkNode(num, edge - 1, nodes)]++;

            for (int next : graph[now]) {
                if (!visited[next]) {
                    q.offer(next);
                }
            }
        }
    }

    // 어떤 노드인지 확인하는 메서드
    public int checkNode(int num, int edge, int[] nodes) {
        if (num % 2 == 1) {
            if (edge % 2 == 1) {
                // 홀 노드
                return 0;
            } else {
                // 역홀 노드
                return 2;
            }
        }
        // 짝
        else {
            if (edge % 2 == 1) {
                // 역짝 노드
                return 3;
            } else {
                // 짝 노드
                return 1;
            }
        }
    }
}