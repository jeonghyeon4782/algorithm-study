import java.io.*;
import java.util.*;

public class ST_6289 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int[] wArr = new int[v + 1];
        int ans = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= v; i++) {
            wArr[i] = Integer.parseInt(st.nextToken());
        }
        List<PriorityQueue<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) {
            graph.add(new PriorityQueue<>(Collections.reverseOrder()));
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).offer(wArr[b]);
            graph.get(b).offer(wArr[a]);
        }
        for (int i = 1; i <= v; i++) {
            int power = wArr[i];
            if (graph.get(i).isEmpty()) {
                ++ans;
                continue;
            }
            int maxPower = graph.get(i).poll();
            if (power > maxPower) ++ans;
        }

        System.out.println(ans);
    }
}