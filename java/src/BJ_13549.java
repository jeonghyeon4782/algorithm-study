import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BJ_13549 {
    static int[] arr;   // 최단 시간
    static int N, K;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        arr = new int[200001];
        Arrays.fill(arr, Integer.MAX_VALUE);
        arr[N] = 0;
        if (N >= K) {
            System.out.println(N - K);
            return;
        } else {
            bfs();
        }
        System.out.println(arr[K]);
    }

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == K) {
                break;
            }

            if (now - 1 > 0 && arr[now - 1] > arr[now] + 1) {
                q.offer(now - 1);
                arr[now - 1] = arr[now] + 1;
            }
            if (now + 1 < 200001 && arr[now + 1] > arr[now] + 1) {
                q.offer(now + 1);
                arr[now + 1] = arr[now] + 1;
            }
            if (now * 2 < 200001 && arr[now * 2] > arr[now]) {
                q.offer(now * 2);
                arr[now * 2] = arr[now];
            }
        }
    }
}
