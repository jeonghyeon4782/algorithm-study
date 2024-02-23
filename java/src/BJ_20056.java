import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BJ_20056 {

    static class FireBall implements Comparable<FireBall> {
        int r, c, m, s, d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        @Override
        public int compareTo(FireBall o) {
            if (this.r != o.r)
                return this.r - o.r;
            return this.c - o.c;
        }

        @Override
        public String toString() {
            return "FireBall [r=" + r + ", c=" + c + ", m=" + m + ", s=" + s + ", d=" + d + "]";
        }

    }

    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N, M, K;
    static List<FireBall> fireBalls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fireBalls = new ArrayList<FireBall>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            fireBalls.add(new FireBall(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }
        while (K-- > 0) {
            move();
            fireBalls.removeIf(f -> f.m <= 0);
        }

        int result = 0;
        for (FireBall f : fireBalls) {
            result += f.m;
        }
        System.out.println(result);
    }

    // 이동
    private static void move() {
        for (FireBall fireBall : fireBalls) {
            fireBall.r += dr[fireBall.d] * fireBall.s;
            fireBall.c += dc[fireBall.d] * fireBall.s;

            // 만약 벗어난다면
            if (fireBall.r < 1) {
                fireBall.r = N - Math.abs(fireBall.r);
            } else if (fireBall.r > N) {
                fireBall.r = fireBall.r - N;
            }
            if (fireBall.c < 1) {
                fireBall.c = N - Math.abs(fireBall.c);
            } else if (fireBall.c > N) {
                fireBall.c = fireBall.c - N;
            }
        }
        // 같은 위치 합치기
        union();
    }

    private static void union() {
        int unionCnt = 1;
        int s = 0, e = 0;
        List<FireBall> addFireBallList = new ArrayList<>(); // 추가할 파이어볼 객체
        List<Integer> removedIdxList = new ArrayList<>();    // 삭제할 인덱스
        Collections.sort(fireBalls);
        for (int i = 0; i < fireBalls.size() - 1; i++) {
            FireBall now = fireBalls.get(i);
            FireBall next = fireBalls.get(i + 1);
            // 다음거랑 같으면?
            if (now.r == next.r && now.c == next.c) {
                next.m += now.m;
                next.s += now.s;
                unionCnt++;
                e = i + 1;  // 다음거랑 같으면 인덱스 증가
                removedIdxList.add(i);
                if (i == fireBalls.size() - 2) {
                    removedIdxList.add(i + 1);
                    int newM = next.m / 5;
                    int newS = next.s / unionCnt;
                    int newD;
                    if (EvenAddCheck(s, e)) newD = 0;
                    else newD = 1;
                    for (int j = 0; j < 4; j++) {
                        addFireBallList.add(new FireBall(now.r, now.c, newM, newS, newD));
                        newD += 2;
                    }
                }
            } else { // 다음거랑 다르면?
                if (unionCnt > 1) {
                    int newM = now.m / 5;
                    int newS = now.s / unionCnt;
                    int newD;
                    if (EvenAddCheck(s, e)) newD = 0;
                    else newD = 1;
                    for (int j = newD; j < 4; j += 2) {
                        addFireBallList.add(new FireBall(now.r, now.c, newM, newS, j));
                    }
                    removedIdxList.add(i);
                }
                s = i + 1;
                unionCnt = 1;
            }
        }
        int cnt = 0;
        for (int idx : removedIdxList) {
            fireBalls.remove(idx - cnt++);
        }
        fireBalls.addAll(addFireBallList);
    }

    // 삭제할 것들이 모두 홀수인지 짝수인지 확인
    private static boolean EvenAddCheck(int s, int e) {
        int odd = 0;
        int even = 0;
        for (int i = s; i <= e; i++) {
            if (fireBalls.get(i).d % 2 == 0) even++;
            else odd++;
        }
        if (odd == 0 || even == 0) return true;
        return false;
    }
}