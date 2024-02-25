import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_21921 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int sumNum = 0;
        int cnt = 1;
        int maxNum = 0;
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (i < X) sumNum += arr[i];
        }
        maxNum = sumNum;
        for (int i = 0; i < N - X; i++) {
            sumNum = sumNum - arr[i] + arr[i + X];
            if (sumNum > maxNum) {
                maxNum = sumNum;
                cnt = 1;
            } else if (sumNum == maxNum) cnt++;
        }
        if (maxNum != 0) {
            System.out.println(maxNum);
            System.out.println(cnt);
        } else {
            System.out.println("SAD");
        }
    }
}
