import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_21608 {

    static class Pos implements Comparable<Pos> {
        int x, y, likeCnt, emptyCnt;

        public Pos(int x, int y, int likeCnt, int emptyCnt) {
            this.x = x;
            this.y = y;
            this.likeCnt = likeCnt;
            this.emptyCnt = emptyCnt;
        }

        @Override
        public String toString() {
            return "Pos [x=" + x + ", y=" + y + ", likeCnt=" + likeCnt + ", emptyCnt=" + emptyCnt + "]";
        }

        @Override
        public int compareTo(Pos o) {
            if (this.likeCnt != o.likeCnt) {
                return Integer.compare(o.likeCnt, this.likeCnt);
            } else {
                if (this.emptyCnt != o.emptyCnt) {
                    return Integer.compare(o.emptyCnt, this.emptyCnt);
                } else {
                    if (this.x != o.x) {
                        return Integer.compare(o.x, this.x);
                    } else {
                        return Integer.compare(o.y, this.y);
                    }
                }
            }
        }
    }

    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int[] index; // 학생의 순서
    static int[][] like; // 좋아하는 사람
    static int[][] map; // 교실 자리
    static int n;
    static Pos[] posMap;
    static int likeScore;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        index = new int[n * n];
        like = new int[n * n + 1][4];
        map = new int[n][n];
        posMap = new Pos[n * n];

        for (int i = 0; i < n * n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            index[i] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                like[index[i]][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int student : index) {
            solve(student);
        }

        like();
        System.out.println(likeScore);
    }

    // 자리 앉히기
    public static void solve(int student) {
        int idx = 0;
        posMap = new Pos[n * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int likeCnt = 0;
                int emptyCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (!isValid(nx, ny))
                        continue;

                    if (map[nx][ny] == 0)
                        emptyCnt++;
                    else if (map[nx][ny] != 0 && likeCheck(student, map[nx][ny]))
                        likeCnt++;
                }
                posMap[idx++] = new Pos(i, j, likeCnt, emptyCnt);
            }
        }
        Arrays.sort(posMap);
        for (int i = 0; i < n * n; i++) {
            if (map[posMap[i].x][posMap[i].y] == 0) {
                map[posMap[i].x][posMap[i].y] = student;
                break;
            }
        }
    }

    // 각 자리에서 주변에 좋아하는 사람 점수 구하기
    public static void like() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int likeCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (!isValid(nx, ny))
                        continue;

                    if (map[nx][ny] != 0 && likeCheck(map[i][j], map[nx][ny]))
                        likeCnt++;
                }
                switch (likeCnt) {
                    case 1:
                        likeScore += 1;
                        break;
                    case 2:
                        likeScore += 10;
                        break;
                    case 3:
                        likeScore += 100;
                        break;
                    case 4:
                        likeScore += 1000;
                        break;
                }
            }
        }
    }

    // from이 to를 좋아하는지 확인
    public static boolean likeCheck(int from, int to) {
        for (int student : like[from]) {
            if (student == to)
                return true;
        }
        return false;
    }

    public static boolean isValid(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
