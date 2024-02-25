import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2252 {
    static int N, M;
    static int[] in;
    static ArrayList<ArrayList<Integer>> students;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        in = new int[N + 1];
        students = new ArrayList<>();
        q = new LinkedList<>();
        for (int i = 0; i < N + 1; i++) {
            students.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            students.get(from).add(to);
            ++in[to];
        }
        for (int i = 1; i <= N; i++) {
            if (in[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            System.out.print(now + " ");

            for (int next : students.get(now)) {
                if (--in[next] == 0) q.offer(next);
            }
        }
    }
}
