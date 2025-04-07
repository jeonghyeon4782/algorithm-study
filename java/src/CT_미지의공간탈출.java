import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class CT_미지의공간탈출 {

    static class Virus {
        int r, c, d, v;

        public Virus(int r, int c, int d, int v) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.v = v;
        }
    }

    static class Point {
        int r, c, turn;

        public Point(int r, int c, int turn) {
            this.r = r;
            this.c = c;
            this.turn = turn;
        }
    }

    // 동서남북
    static int[] dr = { 0, 0, 1, -1 };
    static int[] dc = { 1, -1, 0, 0 };
    static int N, M, F; // N : 미지의 공간 한 변의 길이, M : 시간의 벽 한변의 길이, 시간 이상 현상 갯수
    static int[][] map1, map2; // map1 : 미지의 공간, map2 : 시간의 벽
    static boolean[][] visited1, visited2; // visited1 : 미지의 공간 방문 체크, visited2 : 시간의 벽 방문 체크
    static int outR, outC, inR, inC; // 나가는 r,c, 들어오는 r,c
    static int exitR, exitC, startR, startC;
    static List<Virus> virusList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        map1 = new int[N][N];
        map2 = new int[M * 3][M * 3];
        virusList = new LinkedList<>();

        for (int r = 0; r < M * 3; r++) {
            Arrays.fill(map2[r], -1);
        }

        // 미지의 공간 평면도 만들기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map1[i][j] = Integer.parseInt(st.nextToken());
                if (map1[i][j] == 4) {
                    exitR = i;
                    exitC = j;
                }
            }
        }

        // 시간의 벽 평면도 만들기
        for (int i = 0; i < 5; i++) {
            int[][] partMap = new int[M][M];
            for (int r = 0; r < M; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < M; c++) {
                    partMap[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            // 방향에 맞게 배열 돌린 후 단면도를 평면도에 추가하기
            makeMap2(changeMap(partMap, i), i);
        }

        // 시작 위치 구하기
        for (int r = 0; r < M * 3; r++) {
            for (int c = 0; c < M * 3; c++) {
                if (map2[r][c] == 2) {
                    startR = r;
                    startC = c;
                }
            }
        }

        // 워프 좌표 구하기
        checkWarp();
//		showMap(map1);
//		showMap(map2);
//		System.out.println(exitR + " " + exitC + " " + inR + " " + inC + " " + outR + " " + outC);

        // 바이러스 입력 받기
        for (int i = 0; i < F; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            virusList.add(new Virus(r, c, d, v));
            map1[r][c] = -1;
        }


        // 시간의 벽 >> 미지의 공간 최단 거리 구하기
        int outCnt = countOut();
        if (outCnt == -1) {
            System.out.println(-1);
            return;
        }
        outCnt++;

        // 미지의 공간을 outCnt만큼 확산시키기
        for (int i = 1; i <= outCnt; i++) {
            for (Virus virus : virusList) {
                if (i % virus.v == 0) {
                    int nr = virus.r + dr[virus.d];
                    int nc = virus.c + dc[virus.d];

                    if ((0 <= nr && nr < N && 0 <= nc && nc < N) && map1[nr][nc] != 1 && map1[nr][nc] != 3 && map1[nr][nc] != 4) {
                        map1[nr][nc] = -1;
                        virus.r = nr;
                        virus.c = nc;
                    }
                }
            }
        }

        // 만약 시간의 벽에서 나오기 전에 시간 이상이 확산되면 -1 출력 후 종료
        if (map1[inR][inC] == -1) {
            System.out.println(-1);
            return;
        }

        visited1 = new boolean[N][N];
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(inR, inC, outCnt));
        visited1[inR][inC] = true;

        // 최종 BFS
        while (!queue.isEmpty()) {
            ++outCnt;

            // 바이러스 확산
            for (Virus virus : virusList) {
                if (outCnt % virus.v == 0) {
                    int nr = virus.r + dr[virus.d];
                    int nc = virus.c + dc[virus.d];

                    if ((0 <= nr && nr < N && 0 <= nc && nc < N) && map1[nr][nc] != 1 && map1[nr][nc] != 3 && map1[nr][nc] != 4) {
                        map1[nr][nc] = -1;
                        virus.r = nr;
                        virus.c = nc;
                    }
                }
            }

            // 타임 머신 이동
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point now = queue.poll();

                if (map1[now.r][now.c] == 4) {
                    System.out.println(now.turn);
//					showMap(map1);
                    return;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = now.r + dr[d];
                    int nc = now.c + dc[d];

                    if (!(0 <= nr && nr < N && 0 <= nc && nc < N)) continue;
                    if (map1[nr][nc] == 3 || map1[nr][nc] == 1 || map1[nr][nc] == -1 || visited1[nr][nc]) continue;

                    queue.offer(new Point(nr, nc, now.turn + 1));
                    visited1[nr][nc] = true;
                }
            }
        }

//		showMap(map1);
        System.out.println(-1);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // 시간의 벽에서 탈출하는 시간 구하기
    private static int countOut() {
        visited2 = new boolean[M * 3][M * 3];
        visited2[startR][startC] = true;
        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(startR, startC, 0));

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.r == outR && now.c == outC) {
                return now.turn;
            }

            for (int d = 0; d < 4; d++) {
                int nr = now.r + dr[d];
                int nc = now.c + dc[d];

                // 시간의 벽을 벗어난다면 넘어가기
                if (!(0 <= nr && nr < M * 3 && 0 <= nc && nc < M * 3)) continue;

                // 만약 -1로 간다면? 
                if (map2[nr][nc] == -1) {
                    // 몇 분면인지 구하기
                    // 1분면
                    if (nr < M && nc < M) {
                        nr = now.c;
                        nc = now.r;
                    }
                    // 2분면
                    else if (nr < M && nc >= M * 2) {
                        if (d == 0) {
                            nr = M;
                            nc = now.c + M - now.r;
                        } else if (d == 3) {
                            nr = 3 * M - now.c - 1;
                            nc = 2 * M - 1;
                        }
                    }
                    // 3분면
                    else if (nr >= M * 2 && nc < M) {
                        if (d == 1) {
                            nr = 2 * M - 1;
                            nc = 3 * M - now.r - 1;
                        } else if (d == 2) {
                            nr = 2 * M + now.c;
                            nr = M;
                        }
                    }
                    // 4분면
                    else if (nr >= M * 2 && nc >= M * 2) {
                        nr = now.c;
                        nc = now.r;
                    }
                }

                // 장애물이거나 방문 했던 곳이면 넘어가기
                if (map2[nr][nc] == 1 || visited2[nr][nc]) continue;

                queue.offer(new Point(nr, nc, now.turn + 1));
                visited2[nr][nc] = true;
            }
        }

        return -1;
    }

    // 워프 구하기
    private static void checkWarp() {
        int sR = 0;
        int sC = 0;
        boolean flag = false;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map1[r][c] == 3 && !flag) {
                    sR = r;
                    sC = c;
                    flag = true;
                }
                if (map1[r][c] == 3) {
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if (map1[nr][nc] == 0) {
                            inR = nr;
                            inC = nc;
                        }
                    }
                }
            }
        }

        for (int r = sR; r < sR + M; r++) {
            for (int c = sC; c < sC + M; c++) {
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (map1[nr][nc] == 0) {
                        int targetR = r - sR;
                        int targetC = c - sC;
                        if (d == 0) {
                            outR = M + targetR;
                            outC = 3 * M - 1;
                        } else if (d == 1) {
                            outR = M + targetR;
                            outC = 0;
                        } else if (d == 2) {
                            outR = M * 3 - 1;
                            outC = M + targetC;
                        } else {
                            outR = 0;
                            outC = M + targetC;
                        }

                        return;
                    }
                }
            }
        }
    }


    // 단면도를 평면도에 추가하기
    private static void makeMap2(int[][] changeMap, int d) {
        if (d == 0) {
            // 동쪽 단면도라면
            for (int r = M; r < M * 2; r++) {
                for (int c = 2 * M; c < 3 * M; c++) {
                    map2[r][c] = changeMap[r - M][c - 2 * M];
                }
            }
        } else if (d == 1) {
            // 서쪽 단면도라면
            for (int r = M; r < M * 2; r++) {
                for (int c = 0; c < M; c++) {
                    map2[r][c] = changeMap[r - M][c];
                }
            }
        } else if (d == 2) {
            // 남쪽 단면도라면
            for (int r = 2 * M; r < M * 3; r++) {
                for (int c = M; c < 2 * M; c++) {
                    map2[r][c] = changeMap[r - M * 2][c - M];
                }
            }
        } else if (d == 3) {
            // 북쪽 단면도라면
            for (int r = 0; r < M; r++) {
                for (int c = M; c < 2 * M; c++) {
                    map2[r][c] = changeMap[r][c - M];
                }
            }
        } else {
            // 위쪽 단면도라면
            for (int r = M; r < M * 2; r++) {
                for (int c = M; c < M * 2; c++) {
                    map2[r][c] = changeMap[r - M][c - M];
                }
            }
        }
    }

    // 배열 돌리기 메서드
    private static int[][] changeMap(int[][] partMap, int d) {
        int[][] newMap = new int[M][M];
        if (d == 0) {
            // 동쪽 단면도라면 시계 반대 방향으로 90도 회전
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < M; c++) {
                    newMap[M - c - 1][r] = partMap[r][c];
                }
            }
        } else if (d == 1) {
            // 서쪽 단면도라면 시계 방향으로 90도 회전
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < M; c++) {
                    newMap[c][M - r - 1] = partMap[r][c];
                }
            }
        } else if (d == 3) {
            // 북쪽 단면도라면 시계 방향으로 180도 회전
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < M; c++) {
                    newMap[c][M - r - 1] = partMap[r][c];
                }
            }
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < M; c++) {
                    partMap[r][c] = newMap[r][c];
                }
            }
            for (int r = 0; r < M; r++) {
                for (int c = 0; c < M; c++) {
                    newMap[c][M - r - 1] = partMap[r][c];
                }
            }
        } else {
            // 위쬭 단면도라면 그대로
            return partMap;
        }

        return newMap;
    }

    // 맵의 현재 상태를 보여주는 메서드
    private static void showMap(int[][] map) {
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
}
