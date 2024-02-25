import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_2382 {
    static class Virus implements Comparable<Virus> {
        int r, c, n, d;

        public Virus(int r, int c, int n, int d) {
            this.r = r;
            this.c = c;
            this.n = n;
            this.d = d;
        }

        @Override
        public int compareTo(Virus o) {
            if (this.r != o.r) return Integer.compare(this.r, o.r);
            else if (this.c != o.c) return Integer.compare(this.c, o.c);
            return Integer.compare(this.n, o.n);
        }
    }

    static int[] dr = {0, -1, 1, 0, 0};
    static int[] dc = {0, 0, 0, -1, 1};
    static int[] reverse = {0, 2, 1, 4, 3};
    static int N, M, K;
    static List<Virus> virusList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test = 1; test <= T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());   // N * N
            M = Integer.parseInt(st.nextToken());   // 격리 시간
            K = Integer.parseInt(st.nextToken());   // 군집 갯수
            virusList = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                virusList.add(new Virus(r, c, n, d));
            }
            while (M-- > 0) {
                move();
            }
            System.out.printf("#%d %d\n", test, virusList.stream().mapToInt(v -> v.n).sum());
        }
    }

    public static void move() {
        for (Virus v : virusList) {
            v.r += dr[v.d];
            v.c += dc[v.d];
            if (v.r == 0 || v.r == N - 1 || v.c == 0 || v.c == N - 1) {
                v.n /= 2;
                v.d = reverse[v.d];
            }
        }
        Collections.sort(virusList);
        int size = virusList.size();
        for (int i = 0; i < size - 1; i++) {
            Virus now = virusList.get(i);
            Virus next = virusList.get(i + 1);
            if (now.r == next.r && now.c == next.c) {
                next.n += now.n;
                virusList.remove(i--);
                --size;
            }
        }
    }
}
