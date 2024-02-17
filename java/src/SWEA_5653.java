import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5653 {
    static class Cell {
        int status; // 0 : 비활성, 1 : 활성, 2 : 죽음
        int life;   // 생명력
        int madeTime;   // 만들어진 시간
        int activeTime; // 활성화 될 시간
        int deadTime;   // 죽을 시간

        public Cell(int status, int life, int madeTime, int activeTime, int deadTime) {
            this.status = status;
            this.life = life;
            this.madeTime = madeTime;
            this.activeTime = activeTime;
            this.deadTime = deadTime;
        }
    }

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static Cell[][] map;
    static boolean[][] visited;
    static int N, M, K;
    final static int SIZE = 400;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int p = 1; p <= test; p++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());   // 제한 시간
            map = new Cell[SIZE][SIZE];
            visited = new boolean[SIZE][SIZE];

            for (int i = SIZE / 2; i < SIZE / 2 + N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = SIZE / 2; j < SIZE / 2 + M; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life == 0) continue;
                    map[i][j] = new Cell(0, life, 0, life, life * 2);
                }
            }

            for (int i = 1; i <= K; i++) {
                breed(i);
            }

            int count = 0;
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] != null && map[i][j].status != 2) count++;
                }
            }
            System.out.println("#" + p + " " + count);
        }
    }


    public static void breed(int time) {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == null || map[i][j].status == 2) continue;
                Cell nowCell = map[i][j];
                if (nowCell.status == 1) {  // 활성화 상태라면
                    // 번식 시작
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (map[nx][ny] == null) {  // 번식할 곳이 비어있다면
                            map[nx][ny] = new Cell(0, nowCell.life, time, time + nowCell.life, time + nowCell.life * 2);
                        } else {    // 번식할 곳이 비어있지 않다면
                            if (map[nx][ny].madeTime == time && map[nx][ny].life < nowCell.life) {     // 이번 시간에 만들어 졌고 만약 생명력이 더 크다면 변경
                                map[nx][ny] = new Cell(0, nowCell.life, time, time + nowCell.life, time + nowCell.life * 2);
                            }
                        }
                    }
                    // 만약 죽을 시간이면 죽이기
                    if (nowCell.deadTime == time) nowCell.status = 2;
                } else if (nowCell.status == 0) {   // 비활성화 상태라면
                    if (nowCell.activeTime == time) nowCell.status = 1; // 만약 활성화 시간이면 활성화
                }
            }
        }

    }
}
