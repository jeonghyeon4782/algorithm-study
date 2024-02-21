import java.util.*;

public class BJ_16236 {
    static class Pos implements Comparable<Pos>{
        int x, y, d;

        public Pos(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public int compareTo(Pos other) {
            if (this.d != other.d) {
                return Integer.compare(this.d, other.d); // d를 오름차순으로 정렬
            } else if (this.x != other.x) {
                return Integer.compare(this.x, other.x); // d가 같으면 x를 오름차순으로 정렬
            } else {
                return Integer.compare(this.y, other.y); // d와 x가 같으면 y를 오름차순으로 정렬
            }
        }
    }

    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int N, size, eat, ans;
    static int[][] map;
    static boolean[][] visited;
    static int r, c;
    static List<Pos> eatList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        size = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    r = i;
                    c = j;
                }
            }
        }
        map[r][c] = 0;
        while (bfs());
        System.out.println(ans == 0 ? 0 : ans);
    }

    public static boolean bfs() {
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(r, c, 0));
        visited = new boolean[N][N];
        visited[r][c] = true;
        eatList = new ArrayList<>();

        while (!q.isEmpty()) {
            Pos now = q.poll();
            if (map[now.x][now.y] != 0 && map[now.x][now.y] < size) {
                eatList.add(now);
            }
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (!isValid(nx, ny) || map[nx][ny] > size || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.offer(new Pos(nx, ny, now.d + 1));
            }
        }
        Collections.sort(eatList);
        if (eatList.size() == 0) return false;
        else {
            Pos minPos = eatList.get(0);
            ans += minPos.d;
            map[minPos.x][minPos.y] = 0;
            r = minPos.x;   c = minPos.y;
            ++eat;
            if (eat == size) {
                ++size;
                eat = 0;
            }
            return true;
        }
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
