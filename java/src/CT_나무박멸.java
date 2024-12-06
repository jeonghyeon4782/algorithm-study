import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class CT_나무박멸 {
    static class Herbicide implements Comparable<Herbicide> {
        int r, c, t;    // t : 제초제가 없어지는 시간.

        public Herbicide(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }

        public int compareTo(Herbicide o) {
            return Integer.compare(this.t, o.t);
        }
    }

    static class Tree implements Comparable<Tree> {
        int r, c;    // t : 제초제가 없어지는 시간.

        public Tree(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public int compareTo(Tree o) {
            if (this.r != o.r) return Integer.compare(this.r, o.r);
            return Integer.compare(this.c, o.c);
        }
    }

    // 상, 하, 좌, 우, 상좌, 상우, 하좌, 하우
    static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};

    static int N, M, K, C;  // N : 격자의 크기, M : 진행 년도, K : 제초의 범위, C : 제초제의 유통기한
    static int[][] map; // -1 : 벽 또는 제초제, 0 : 빈칸, 1이상 : 나무
    static boolean[][] visited; // 방문 체크
    static PriorityQueue<Herbicide> pq; // 제초제 리스트
    static List<Tree> treeList; // 살아있는 나무들의 리스트

    public static void main(String[] args) throws IOException {
        // setting
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int time = 0;
        map = new int[N][N];
        pq = new PriorityQueue<>();
        treeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] > 0) treeList.add(new Tree(i, j));
            }
        }

        int answer = 0;
        while (time < M) {
            // 시간 증가
            ++time;

            // 제초제 제거
            deleteHerbicide(time);

            // 나무 성장 시키기
            growTree();

            // 나무 번식 시키기
            reproduceTree();

            // 박멸 장소 찾기
            int[] point = searchHerbicidePoint();
            answer += point[2];

            // 제초제 추가
            addHerbicide(point[0], point[1], time + C + 1);
        }

        System.out.println(answer);
    }

    // 제초제 추가시키기
    private static void addHerbicide(int targetR, int targetC, int time) {

        // 제초제 추가
        map[targetR][targetC] = -1;
        Herbicide h = existHerbicide(targetR, targetC);
        if (h == null) {
            pq.offer(new Herbicide(targetR, targetC, time));
        } else {
            pq.remove(h);
            pq.offer(new Herbicide(targetR, targetC, time));
        }

        for (int d = 4; d < 8; d++) {
            int r = targetR;
            int c = targetC;
            int dist = 0;
            boolean zeroFlag = false;
            while (dist < K) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                dist++;

                if (!isValid(nr, nc) || map[nr][nc] == -1) {
                    break;
                }
                if (map[nr][nc] == 0) zeroFlag = true;
                // 제초제 추가
                map[nr][nc] = -1;
                // 제초제 리스트에 존재하지 않는다면?
                h = existHerbicide(nr, nc);
                if (h == null) {
                    pq.offer(new Herbicide(nr, nc, time));
                } else {
                    pq.remove(h);
                    pq.offer(new Herbicide(nr, nc, time));
                }

                r = nr;
                c = nc;

                if (zeroFlag) {
                    break;
                }
            }
        }

        int size = treeList.size();
        for (int i = 0; i < size; i++) {
            Tree t = treeList.get(i);
            for (Herbicide herb : pq) {
                if (t.c == herb.c && t.r == herb.r) {
                    treeList.remove(i);
                    size--;
                    i--;
                }
            }
        }
    }

    // 제초제 큐에 해당 r, c가 존재하는 지 여부 판단 메서드
    private static Herbicide existHerbicide(int r, int c) {
        for (Herbicide h : pq) {
            if (h.r == r && h.c == c) return h;
        }
        return null;
    }

    // 제초제를 뿌릴 장소를 찾기 메서드
    private static int[] searchHerbicidePoint() {
        int[] answer = {0, 0, 0};
        int maxCnt = 0;
        // 행 오름차순, 열 오름차순 정렬
        Collections.sort(treeList);

        for (Tree tree : treeList) {
            int cnt = map[tree.r][tree.c];    // 박멸하는 나무 수
            // 방향별로 박멸 나무 수를 구한 뒤 cnt에 합하기
            for (int d = 4; d < 8; d++) {
                int r = tree.r;
                int c = tree.c;
                int dist = 0;
                while (dist < K) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    dist++;
                    // 만약 벽이나 빈칸, 맵의 밖으로 나가면 그 방향은 끝
                    if (!isValid(nr, nc) || map[nr][nc] <= 0) {
                        break;
                    }
                    cnt += map[nr][nc];
                    r = nr;
                    c = nc;
                }
            }
            if (maxCnt < cnt) {
                maxCnt = cnt;
                answer[0] = tree.r;
                answer[1] = tree.c;
            }
        }
        answer[2] = maxCnt;
        return answer;
    }

    // 나무를 번식시키는 메서드
    private static void reproduceTree() {

        // 번식 전 나무의 상태를 보관
        visited = new boolean[N][N];
        for (Tree tree : treeList) {
            visited[tree.r][tree.c] = true;
        }

        // 번식 장소 구하기
        int size = treeList.size();
        for (int i = 0; i < size; i++) {
            int r = treeList.get(i).r;
            int c = treeList.get(i).c;
            List<int[]> reproducePoint = new ArrayList<>(); // 번식할 장소를 넣어두는 리스트
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (isValid(nr, nc) && !visited[nr][nc] && map[nr][nc] >= 0) {
                    reproducePoint.add(new int[]{nr, nc});
                }
            }

            // 만약 번식을 못하는 나무라면 통과 시키기
            if (reproducePoint.isEmpty()) continue;

            // 번식 시키기
            int growCnt = map[r][c] / reproducePoint.size();
            if (growCnt < 1) continue;

            for (int[] point : reproducePoint) {
                // 만약 처음 생기는 나무라면 나무 리스트에 추가
                if (map[point[0]][point[1]] == 0) {
                    treeList.add(new Tree(point[0], point[1]));
                }
                map[point[0]][point[1]] += growCnt;
            }
        }
    }

    // 나무를 성장시키는 메서드
    private static void growTree() {
        for (Tree tree : treeList) {
            int cnt = 0;    // 주변 나무의 갯수
            for (int d = 0; d < 4; d++) {
                int nr = tree.r + dr[d];
                int nc = tree.c + dc[d];
                if (isValid(nr, nc) && map[nr][nc] > 0) {
                    cnt++;
                }
            }
            map[tree.r][tree.c] += cnt;
        }
    }

    // 현재 시간에 맞게 맵에서 제초제를 제거하는 메서드
    private static void deleteHerbicide(int time) {
        // 만약 제초제가 없으면 그냥 리턴
        if (pq.isEmpty()) return;

        // 큐에서 제초제를 하나씩 꺼내며 처리
        while (!pq.isEmpty()) {
            Herbicide herbicide = pq.peek();  // 큐의 가장 앞에 있는 제초제 확인
            if (herbicide.t <= time) {  // 제초제의 유통기한이 지났다면
                pq.poll();  // 제초제 제거
                map[herbicide.r][herbicide.c] = 0;  // 맵에서 제초제 제거
            } else {
                break;  // 제초제의 유통기한이 남아있다면 더 이상 처리할 필요 없음
            }
        }
    }


    // 맵 밖으로 나가는지 판단하는 메서드
    private static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    // map을 출력하는 메서드
    private static void showMap() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
}
