import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20922 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0;
        int e = 0;
        int[] numCount = new int[100001];
        int answer = 0;

        while (e < N) {
            if (numCount[nums[e]] < K) {
                numCount[nums[e]]++;
                e++;
                answer = Math.max(answer, e - s);
            } else {
                numCount[nums[s]]--;
                s++;
            }
        }

        System.out.println(answer);
    }
}
