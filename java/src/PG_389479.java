import java.util.*;

class PG_389479 {

    class Server implements Comparable<Server> {
        int time, cnt;

        public Server(int time, int cnt) {
            this.time = time;
            this.cnt = cnt;
        }

        public int compareTo(Server o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public int solution(int[] players, int m, int k) {
        PriorityQueue<Server> pq = new PriorityQueue<>();
        int ans = 0;
        int now = 1;

        for (int t = 1; t <= 24; t++) {
            int player = players[t - 1];
            if (player >= m * now) {
                int plus = (player / m + 1) - now;
                ans += plus;
                now += plus;
                pq.offer(new Server(t - 1 + k, plus));
            }

            if (!pq.isEmpty() && pq.peek().time == t) {
                Server server = pq.poll();
                now -= server.cnt;
            }
        }

        return ans;
    }
}