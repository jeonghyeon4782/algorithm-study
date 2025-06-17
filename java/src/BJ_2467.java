import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2467 {

    static int N;
    static int[] arr;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        // 1. setting
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        answer = new int[2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 투포인터
        int s = 0;
        int e = arr.length - 1;
        int target = Integer.MAX_VALUE;
        while (s < e) {
            int sum = arr[s] + arr[e];
            if (Math.abs(sum) <= target) {
                answer[0] = arr[s];
                answer[1] = arr[e];
                target = Math.abs(sum);
            }
            if (sum >= 0) {
                e--;
            } else {
                s++;
            }
        }

        // 3. 정답 출력
        Arrays.sort(answer);
        StringBuilder sb = new StringBuilder();
        sb.append(answer[0]).append(" ").append(answer[1]);
        System.out.println(sb.toString());
    }
}