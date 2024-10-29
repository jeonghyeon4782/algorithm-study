import java.io.*;
import java.util.*;

public class ST_7628 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int answer = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 2; i <= 100; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (arr[j] % i == 0) cnt++;
            }
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}