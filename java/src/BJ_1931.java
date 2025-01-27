import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1931 {
    static class Time implements Comparable<Time>{
        int s, e;

        public Time(int s, int e) {
            this.s = s;
            this.e = e;
        }

        public int compareTo(Time o) {
            if (this.e != o.e) {
                return Integer.compare(this.e, o.e);
            } else {
                return Integer.compare(this.s, o.s);
            }
        }

        @Override
        public String toString() {
            return "Time{" +
                    "s=" + s +
                    ", e=" + e +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Time> times = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times.add(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int curE = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            Time time = times.poll();
            if (time.s >= curE) {
                ++ans;
                curE = time.e;
            }
        }
        System.out.println(ans);
    }
}
