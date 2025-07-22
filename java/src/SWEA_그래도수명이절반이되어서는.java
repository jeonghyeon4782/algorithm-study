import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_그래도수명이절반이되어서는 {

    static int N, K;
    static int[] arr, part;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int test = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int T = 1; T <= test; T++) {

            // 1. setting
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N];
            part = new int[K];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < K; i++) part[i] = Integer.parseInt(st.nextToken());

            // 2. 이진 탐색
            answer = 200000;
            binarySearch();

            // 3. 출력
            sb.append("#").append(T).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static void binarySearch() {
        int s = 0;
        int e = 200000;
        while (s <= e) {
            int m = (s + e) / 2;
            boolean flag = isValid(m);
            if (flag) {
                answer = m;
                e = m - 1;
            } else {
                s = m + 1;
            }
        }
    }

    // num이 가능한 지?
    private static boolean isValid(int num) {
        int partIdx = 0;
        int cnt = 0;
        for (int a : arr) {
            if (a <= num) cnt++;
            else cnt = 0;
            if (part[partIdx] == cnt) {
                partIdx++;
                cnt = 0;
            }
            if (partIdx == K) return true;
        }
        return false;
    }
}
