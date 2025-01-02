import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_16928 {

    static class Pos {
        int x, time;

        public Pos(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }

    static int N, M;
    static Map<Integer, Integer> ladder;
    static Map<Integer, Integer> snake;
    static boolean[] visited;
    static Queue<Pos> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ladder = new HashMap<>();
        snake = new HashMap<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ladder.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            snake.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        visited = new boolean[101];
        q = new LinkedList<>();
        for (int i = 2; i <= 7; i++) {
            if (ladder.containsKey(i)) {
                visited[i] = true;
                visited[ladder.get(i)] = true;
                q.offer(new Pos(ladder.get(i), 1));
            }
            else if (snake.containsKey(i)) {
                visited[i] = true;
                visited[snake.get(i)] = true;
                q.offer(new Pos(snake.get(i), 1));
            }
            else {
                visited[i] = true;
                q.offer(new Pos(i, 1));
            }
        }

        while (!q.isEmpty()) {
            Pos now = q.poll();
            if (now.x == 100) {
                System.out.println(now.time);
                return;
            }

            for (int i = 1; i <= 6; i++) {
                int next = now.x + i;

                if (next <= 100 && !visited[next]) {

                    if (ladder.containsKey(next)) {
                        visited[next] = true;
                        next = ladder.get(next);
                        visited[next] = true;
                        q.offer(new Pos(next, now.time + 1));
                    }

                    else if (snake.containsKey(next)) {
                        visited[next] = true;
                        next = snake.get(next);
                        visited[next] = true;
                        q.offer(new Pos(next, now.time + 1));
                    }

                    else {
                        visited[next] = true;
                        q.offer(new Pos(next, now.time + 1));
                    }
                }
            }
        }
    }
}
