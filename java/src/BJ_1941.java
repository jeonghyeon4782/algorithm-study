import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_1941 {

    static class Pos {
        int x, y;
        char name;
        public Pos(int x, int y, char name) {
            this.x = x;
            this.y = y;
            this.name = name;
        }
    }

    // 우, 좌, 하, 상
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Pos[] input;
    static Pos[] students;
    static int answer;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input = new Pos[7];
        students = new Pos[25];
        int idx = 0;
        for (int i = 0; i < 5; i++) {
            String s = sc.next();
            for (int j = 0; j < 5; j++) {
                students[idx++] = new Pos(i, j, s.charAt(j));
            }
        }
        comb(0, 0);
        System.out.println(answer);
    }

    // 모든 조합 생성
    private static void comb(int cnt, int start) {
        if (cnt == 7) {
            if (check()) answer++;
            return;
        }
        for (int i = start; i < 25; i++) {
            input[cnt] = students[i];
            comb(cnt + 1, i + 1);
        }
    }

    // 가능한 조합인지 체크
    private static boolean check() {
        int s = 0, y = 0;
        map = new int[5][5];
        visited = new boolean[5][5];
        for (Pos p : input) {
            if (p.name == 'S') s++;
            else y++;
            map[p.x][p.y] = 1;
        }
        if (s < 4) return false;    // 이다솜파가 최소 4명 이상이어야 한다.
        int area = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    bfs(i, j);
                    area++;
                }
            }
        }
        if (area != 1) return false;
        return true;
    }

    private static void bfs(int x, int y) {
        visited[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {x, y});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = now[0] + dx[d];
                int ny = now[1] + dy[d];

                if (0 <= nx && nx < 5 && 0 <= ny && ny < 5 && !visited[nx][ny] && map[nx][ny] == 1) {
                    q.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }
}
